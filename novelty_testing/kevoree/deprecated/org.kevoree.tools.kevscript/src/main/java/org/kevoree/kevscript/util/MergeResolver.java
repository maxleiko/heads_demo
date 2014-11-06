package org.kevoree.kevscript.util;

import org.kevoree.ContainerRoot;
import org.kevoree.Repository;
import org.kevoree.compare.DefaultModelCompare;
import org.kevoree.loader.XMIModelLoader;
import org.kevoree.log.Log;
import org.kevoree.resolver.MavenResolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created with IntelliJ IDEA.
 * User: duke
 * Date: 25/11/2013
 * Time: 17:11
 */
public class MergeResolver {

    private static MavenResolver resolver = new MavenResolver();
    private static XMIModelLoader loader = new XMIModelLoader();
    private static DefaultModelCompare compare = new DefaultModelCompare();

    public static void merge(ContainerRoot model, String type, String url) {
        if (type.equals("mvn")) {
            List<String> urls = new ArrayList<String>();
            for (Repository repo : model.getRepositories()) {
                urls.add(repo.getUrl());
            }
            File resolved = resolver.resolve(url, urls);
            if (resolved != null && resolved.exists()) {
                try {
                    JarFile jar = new JarFile(new File(resolved.getAbsolutePath()));
                    JarEntry entry = jar.getJarEntry("KEV-INF/lib.kev");
                    ContainerRoot remoteModel = (ContainerRoot) loader.loadModelFromStream(jar.getInputStream(entry)).get(0);
                    compare.merge(model, remoteModel).applyOn(model);
                } catch (IOException e) {
                    Log.error("Bad JAR file ", e);
                }
            }
        }
    }

}
