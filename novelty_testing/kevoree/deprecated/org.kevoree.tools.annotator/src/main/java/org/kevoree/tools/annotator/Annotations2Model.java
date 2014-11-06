package org.kevoree.tools.annotator;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import org.kevoree.ContainerRoot;
import org.kevoree.DeployUnit;
import org.kevoree.factory.DefaultKevoreeFactory;
import org.kevoree.factory.KevoreeFactory;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: duke
 * Date: 24/11/2013
 * Time: 14:40
 */
public class Annotations2Model {

    private static final KevoreeFactory factory = new DefaultKevoreeFactory();

    private void recursiveBuild(File current, ClassPool pool, String root, DeployUnit du, ContainerRoot modelRoot) throws Exception {
        if (current.isDirectory()) {
            File[] childs = current.listFiles();
            for (int i = 0; i < childs.length; i++) {
                File child = childs[i];
                if (child.getName().endsWith(".class")) {
                    String className = root + "." + child.getName().replace(".class", "");
                    CtClass clazz = pool.get(className);
                    Object[] annotations = clazz.getAvailableAnnotations();
                    for (Object annotation : annotations) {
                        ModelBuilderHelper.process(annotation, clazz, factory, du, modelRoot);
                    }
                } else {
                    if (child.isDirectory()) {
                        String nextPath = root;
                        if (!root.isEmpty()) {
                            nextPath = nextPath + "." + child.getName();
                        } else {
                            nextPath = child.getName();
                        }
                        recursiveBuild(child, pool, nextPath, du, modelRoot);
                    }
                }
            }
        }
    }

    public void fillModel(File targetDir, ContainerRoot model, DeployUnit deployUnit, List<String> additionalClassPathElems) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(Annotations2Model.class));
        for (String classPath : additionalClassPathElems) {
            pool.appendClassPath(classPath);
        }
        recursiveBuild(targetDir, pool, "", deployUnit, model);
    }

}
