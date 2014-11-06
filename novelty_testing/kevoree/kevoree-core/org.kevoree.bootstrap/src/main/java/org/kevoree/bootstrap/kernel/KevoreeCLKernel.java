package org.kevoree.bootstrap.kernel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kevoree.*;
import org.kevoree.api.BootstrapService;
import org.kevoree.api.Context;
import org.kevoree.api.ModelService;
import org.kevoree.api.PlatformService;
import org.kevoree.api.helper.KModelHelper;
import org.kevoree.bootstrap.Bootstrap;
import org.kevoree.bootstrap.reflect.KevoreeInjector;
import org.kevoree.core.impl.ContextAwareAdapter;
import org.kevoree.core.impl.KevoreeCoreBean;
import org.kevoree.kcl.api.FlexyClassLoader;
import org.kevoree.kcl.api.FlexyClassLoaderFactory;
import org.kevoree.kcl.api.ResolutionPriority;
import org.kevoree.log.Log;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: duke
 * Date: 25/11/2013
 * Time: 19:41
 */
public class KevoreeCLKernel implements BootstrapService {

    private Bootstrap bs;

    public KevoreeCLKernel(Bootstrap b) {
        this.bs = b;
    }

    private Boolean offline = false;

    public void setInjector(KevoreeInjector injector) {
        this.injector = injector;
    }

    private KevoreeInjector injector = null;

    public String buildKernelKey(DeployUnit deployUnit) {
        StringBuilder builder = new StringBuilder();
        builder.append("mvn:");
        builder.append(KModelHelper.fqnGroup(deployUnit));
        builder.append(":");
        builder.append(deployUnit.getName());
        builder.append(":");
        if (deployUnit.getName().equals("org.kevoree.api") || deployUnit.getName().equals("org.kevoree.annotation.api") || deployUnit.getName().equals("org.kevoree.model")) {
            builder.append(bs.getCore().getFactory().getVersion());
        } else {
            builder.append(deployUnit.getVersion());
        }
        return builder.toString();
    }

    @Override
    public FlexyClassLoader get(DeployUnit deployUnit) {
        return bs.getKernel().get(buildKernelKey(deployUnit));
    }

    public FlexyClassLoader installDeployUnit(DeployUnit deployUnit) {
        FlexyClassLoader resolvedKCL = get(deployUnit);
        if (resolvedKCL != null) {
            return resolvedKCL;
        } else {
            HashSet<String> urls = new HashSet<String>();
            if (!offline) {
                ContainerRoot root = KModelHelper.root(deployUnit);
                File resolved;
                for (Repository repo : root.getRepositories()) {
                    urls.add(repo.getUrl());
                }
                if (deployUnit.getVersion().contains("SNAPSHOT") || deployUnit.getVersion().contains("LATEST")) {
                    urls.add("http://oss.sonatype.org/content/groups/public/");
                } else {
                    urls.add("http://repo1.maven.org/maven2");
                }
                Log.info("Resolving ............. " + deployUnit.path());
                long before = System.currentTimeMillis();
                if (deployUnit.getUrl() == null || "".equals(deployUnit.getUrl())) {
                    resolved = bs.getKernel().getResolver().resolve(KModelHelper.fqnGroup(deployUnit), deployUnit.getName(), deployUnit.getVersion(), "jar", urls);
                } else {
                    resolved = bs.getKernel().getResolver().resolve(deployUnit.getUrl(), urls);
                    if (resolved == null && new File(deployUnit.getUrl()).exists()) {
                        resolved = new File(deployUnit.getUrl());
                    }
                }
                Log.info("Resolved in {}ms", (System.currentTimeMillis() - before));
                if (resolved != null) {
                    FlexyClassLoader installed = bs.getKernel().put(buildKernelKey(deployUnit), resolved);
                    return installed;
                } else {
                    Log.error("Unable to resolve {}", deployUnit.path());
                }
            }

        }
        return null;
    }

    @Override
    public void removeDeployUnit(DeployUnit deployUnit) {
        bs.getKernel().drop(buildKernelKey(deployUnit));
    }

    public FlexyClassLoader recursiveInstallDeployUnit(DeployUnit du) {
        FlexyClassLoader previousDu;
        previousDu = get(du);
        if (previousDu != null) {
            return previousDu;
        }
        FlexyClassLoader kcl = installDeployUnit(du);
        if (kcl == null) {
            Log.error("Can't install {}", du.path());
        } else {
            for (DeployUnit child : du.getRequiredLibs()) {
                kcl.attachChild(recursiveInstallDeployUnit(child));
            }
        }
        return kcl;
    }

    @Nullable
    @Override
    public FlexyClassLoader installTypeDefinition(TypeDefinition tdef) {
        FlexyClassLoader kcl = FlexyClassLoaderFactory.INSTANCE.create();
        kcl.resolutionPriority = ResolutionPriority.CHILDS;
        kcl.setKey(tdef.path());
        for (DeployUnit du : tdef.getDeployUnits()) {
            if (filter(du)) {
                kcl.attachChild(recursiveInstallDeployUnit(du));
            }
        }
        return kcl;
    }

    public boolean filter(DeployUnit du) {
        if (du.findFiltersByID("runtime") == null || du.findFiltersByID("runtime").equals("java")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setOffline(boolean b) {
        offline = b;
    }

    private String nodeName;

    public void setNodeName(String nName) {
        nodeName = nName;
    }

    public KevoreeCoreBean core;

    public void setCore(KevoreeCoreBean core) {
        this.core = core;
    }

    @Override
    public Object createInstance(final Instance instance, FlexyClassLoader classLoader) {
        try {
            Class clazz = classLoader.loadClass(instance.getTypeDefinition().findMetaDataByID("java.class").getValue());
            Object newInstance = clazz.newInstance();
            KevoreeInjector selfInjector = injector.clone();
            selfInjector.addService(Context.class, new InstanceContext(instance.path(), nodeName, instance.getName()));
            selfInjector.addService(ModelService.class, new ContextAwareAdapter(core, instance.path()));
            selfInjector.addService(PlatformService.class, core);
            selfInjector.process(newInstance);
            return newInstance;
        } catch (Exception e) {
            Log.error("Error while creating instance {}", e, instance.getTypeDefinition().getName());
            Log.error("Inconsistency in typeDefinition {}", core.getFactory().createJSONSerializer().serialize(instance.getTypeDefinition()));
        }
        return null;
    }

    @NotNull
    @Override
    public void injectDictionary(Instance instance, Object target, boolean defaultOnly) {
        if (instance.getTypeDefinition() == null || instance.getTypeDefinition().getDictionaryType() == null) {
            return;
        }
        for (DictionaryAttribute att : instance.getTypeDefinition().getDictionaryType().getAttributes()) {
            String defValue = null;
            String value = null;
            if (att.getFragmentDependant()) {
                FragmentDictionary fdico = instance.findFragmentDictionaryByID(nodeName);
                if (fdico != null) {
                    Value tempValue = fdico.findValuesByID(att.getName());
                    if (tempValue != null) {
                        value = tempValue.getValue();
                    }
                }
            }
            if (value == null) {
                if (instance.getDictionary() != null) {
                    Value tempValue = instance.getDictionary().findValuesByID(att.getName());
                    if (tempValue != null) {
                        value = tempValue.getValue();
                    }
                }
            }
            if (att.getDefaultValue() != null && !att.getDefaultValue().equals("")) {
                defValue = att.getDefaultValue();
            }
            if (defaultOnly) {
                if (defValue != null && value == null) {
                    internalInjectField(att.getName(), defValue, target);
                }
            } else {
                if (value == null && defValue != null) {
                    value = defValue;
                }
                if (value != null) {
                    internalInjectField(att.getName(), value, target);
                }
            }
        }
    }

    @NotNull
    @Override
    public void injectDictionaryValue(Value dictionaryValue, Object target) {
        internalInjectField(dictionaryValue.getName(), dictionaryValue.getValue(), target);
    }

    private boolean internalInjectField(String fieldName, String value, Object target) {
        if (value != null/* && !value.equals("")*/) {
            try {
                boolean isSet = false;
                String setterName = "set";
                setterName = setterName + fieldName.substring(0, 1).toUpperCase();
                if (fieldName.length() > 1) {
                    setterName = setterName + fieldName.substring(1);
                }
                Method setter = lookupSetter(setterName, target.getClass());
                if (setter != null && setter.getParameterTypes().length == 1) {
                    if (!setter.isAccessible()) {
                        setter.setAccessible(true);
                    }
                    Class pClazz = setter.getParameterTypes()[0];
                    if (pClazz.equals(boolean.class)) {
                        setter.invoke(target, Boolean.parseBoolean(value));
                        isSet = true;
                    }
                    if (pClazz.equals(Boolean.class)) {
                        setter.invoke(target, new Boolean(Boolean.parseBoolean(value)));
                        isSet = true;
                    }
                    if (pClazz.equals(int.class)) {
                        setter.invoke(target, Integer.parseInt(value));
                        isSet = true;
                    }
                    if (pClazz.equals(Integer.class)) {
                        setter.invoke(target, new Integer(Integer.parseInt(value)));
                        isSet = true;
                    }
                    if (pClazz.equals(long.class)) {
                        setter.invoke(target, Long.parseLong(value));
                        isSet = true;
                    }
                    if (pClazz.equals(Long.class)) {
                        setter.invoke(target, new Long(Long.parseLong(value)));
                        isSet = true;
                    }
                    if (pClazz.equals(double.class)) {
                        setter.invoke(target, Double.parseDouble(value));
                        isSet = true;
                    }
                    if (pClazz.equals(Double.class)) {
                        setter.invoke(target, new Double(Double.parseDouble(value)));
                        isSet = true;
                    }
                    if (pClazz.equals(String.class)) {
                        setter.invoke(target, value);
                        isSet = true;
                    }
                    if (pClazz.equals(short.class)) {
                        setter.invoke(target, Short.parseShort(value));
                        isSet = true;
                    }
                    if (pClazz.equals(Short.class)) {
                        setter.invoke(target, new Short(Short.parseShort(value)));
                        isSet = true;
                    }
                    if (pClazz.equals(float.class)) {
                        setter.invoke(target, Float.parseFloat(value));
                        isSet = true;
                    }
                    if (pClazz.equals(Float.class)) {
                        setter.invoke(target, new Float(Float.parseFloat(value)));
                        isSet = true;
                    }
                    if (pClazz.equals(byte.class)) {
                        setter.invoke(target, Byte.parseByte(value));
                        isSet = true;
                    }
                    if (pClazz.equals(Byte.class)) {
                        setter.invoke(target, new Byte(Byte.parseByte(value)));
                        isSet = true;
                    }
                    if (value.length() == 1) {
                        if (pClazz.equals(char.class)) {
                            setter.invoke(target, value.charAt(0));
                            isSet = true;
                        }
                    }
                }

                if (!isSet) {
                    Field f = lookup(fieldName, target.getClass());
                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }
                    if (f.getType().equals(boolean.class)) {
                        f.setBoolean(target, Boolean.parseBoolean(value));
                    }
                    if (f.getType().equals(Boolean.class)) {
                        f.set(target, new Boolean(Boolean.parseBoolean(value)));
                    }
                    if (f.getType().equals(int.class)) {
                        f.setInt(target, Integer.parseInt(value));
                    }
                    if (f.getType().equals(Integer.class)) {
                        f.set(target, new Integer(Integer.parseInt(value)));
                    }
                    if (f.getType().equals(long.class)) {
                        f.setLong(target, Long.parseLong(value));
                    }
                    if (f.getType().equals(Long.class)) {
                        f.set(target, new Long(Long.parseLong(value)));
                    }
                    if (f.getType().equals(double.class)) {
                        f.setDouble(target, Double.parseDouble(value));
                    }
                    if (f.getType().equals(Double.class)) {
                        f.set(target, new Double(Double.parseDouble(value)));
                    }
                    if (f.getType().equals(String.class)) {
                        f.set(target, value);
                    }
                    if (f.getType().equals(short.class)) {
                        f.set(target, Short.parseShort(value));
                    }
                    if (f.getType().equals(Short.class)) {
                        f.set(target, new Short(Short.parseShort(value)));
                    }
                    if (f.getType().equals(float.class)) {
                        f.set(target, Float.parseFloat(value));
                    }
                    if (f.getType().equals(Float.class)) {
                        f.set(target, new Float(Float.parseFloat(value)));
                    }
                    if (f.getType().equals(byte.class)) {
                        f.set(target, Byte.parseByte(value));
                    }
                    if (f.getType().equals(Byte.class)) {
                        f.set(target, new Byte(Byte.parseByte(value)));
                    }
                    if (value.length() == 1) {
                        if (f.getType().equals(char.class)) {
                            f.set(target, value.charAt(0));
                        }
                    }
                }
                return true;
            } catch (Exception e) {
                Log.error("No field corresponding to annotation, consistency error {} on {}", e, fieldName, target.getClass().getName());
                return false;
            }
        } else {
            return false;
        }
    }

    public Method lookupSetter(String name, Class clazz) {
        Method f = null;
        for (Method loopMethod : clazz.getDeclaredMethods()) {
            if (name.equals(loopMethod.getName())) {
                f = loopMethod;
            }
        }
        if (f != null) {
            return f;
        } else {
            for (Class loopClazz : clazz.getInterfaces()) {
                f = lookupSetter(name, loopClazz);
                if (f != null) {
                    return f;
                }
            }
            if (clazz.getSuperclass() != null) {
                f = lookupSetter(name, clazz.getSuperclass());
                if (f != null) {
                    return f;
                }
            }
        }
        return f;
    }

    public Field lookup(String name, Class clazz) {
        Field f = null;
        for (Field loopf : clazz.getDeclaredFields()) {
            if (name.equals(loopf.getName())) {
                f = loopf;
            }
        }
        if (f != null) {
            return f;
        } else {
            for (Class loopClazz : clazz.getInterfaces()) {
                f = lookup(name, loopClazz);
                if (f != null) {
                    return f;
                }
            }
            if (clazz.getSuperclass() != null) {
                f = lookup(name, clazz.getSuperclass());
                if (f != null) {
                    return f;
                }
            }
        }
        return f;
    }


    @Override
    public void injectService(Class<? extends Object> aClass, Object o, Object o2) {
        KevoreeInjector injector = new KevoreeInjector();
        injector.addService(aClass, o);
        injector.process(o2);
    }

    @Nullable
    @Override
    public File resolve(String url, Set<? extends String> repos) {
        return bs.getKernel().getResolver().resolve(url, (Set<String>) repos);
    }

}
