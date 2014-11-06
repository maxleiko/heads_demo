/**
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/* $Id: LoadNewLibCommand.java 13269 2010-11-02 14:24:49Z hrambelo $ 
 * License    : EPL 								
 * Copyright  : IRISA / INRIA / Universite de Rennes 1 */
package org.kevoree.tools.ui.editor.command;

import org.kevoree.ContainerRoot;
import org.kevoree.factory.DefaultKevoreeFactory;
import org.kevoree.pmodeling.api.json.JSONModelLoader;
import org.kevoree.tools.ui.editor.KevoreeUIKernel;
import org.kevoree.tools.ui.editor.PositionedEMFHelper;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.Random;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ffouquet
 */
public class LoadNewLibCommand implements Command {

    public void setKernel(KevoreeUIKernel kernel) {
        this.kernel = kernel;
    }

    private KevoreeUIKernel kernel;

    @Override
    public void execute(Object p) {

        String path = null;
        String absolutePath = null;
        try {
            if (p instanceof File) {
                File file = (File) p;
                absolutePath = file.getAbsolutePath();
            } else if (p instanceof URL) {
                absolutePath = ((URL) p).getPath();
            } else if (p instanceof String) {
                absolutePath = (String) p;
            } else {
                throw new Exception("Unable to load library from object " + p.getClass() + ":" + p.toString());
            }
            JSONModelLoader loader = new JSONModelLoader(new DefaultKevoreeFactory());
            try {
                JarFile jar;
                jar = new JarFile(new File(absolutePath));
                JarEntry entry = jar.getJarEntry("KEV-INF/lib.json");
                if (entry != null) {
                    ContainerRoot nroot = (ContainerRoot) loader.loadModelFromStream(jar.getInputStream(entry)).get(0);
                    kernel.getModelHandler().merge(nroot);
                    PositionedEMFHelper.updateModelUIMetaData(kernel);
                    LoadModelCommand loadCmd = new LoadModelCommand();
                    loadCmd.setKernel(kernel);
                    loadCmd.execute(kernel.getModelHandler().getActualModel());
                    path = convertStreamToFile(jar.getInputStream(entry));
                } else {
                    JOptionPane.showMessageDialog(kernel.getModelPanel(), "No Kev model have been found in the given library.", "Corrupted Lib.", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {
                path = absolutePath;
            }

            if (path != null) {


            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(kernel.getModelPanel(), "Unable to load the give library.\nSee log for further information.", "Unable to load lib.", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(LoadNewLibCommand.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


    private String convertStreamToFile(InputStream inputStream) throws IOException {
        Random rand = new Random();
        File temp = File.createTempFile("kevoreeloaderLib" + rand.nextInt(), ".xmi");
        // Delete temp file when program exits.
        temp.deleteOnExit();
        OutputStream out = new FileOutputStream(temp);
        int read = 0;
        byte[] bytes = new byte[1024];
        while ((read = inputStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        inputStream.close();
        out.flush();
        out.close();

        return temp.getAbsolutePath();
    }
}
