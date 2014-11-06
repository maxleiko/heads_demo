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
package org.kevoree.platform.standalone.gui;

import com.explodingpixels.macwidgets.MacUtils;
import com.explodingpixels.macwidgets.UnifiedToolBar;
import org.kevoree.kcl.api.FlexyClassLoader;
import org.kevoree.microkernel.KevoreeKernel;
import org.kevoree.microkernel.impl.KevoreeMicroKernelImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.SortedSet;

public class KevoreeGUIFrame extends JFrame {

    public static KevoreeGUIFrame singleton = null;

    public KevoreeGUIFrame(String nodeName, String groupName, String hostName) throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        singleton = this;
        MacUtils.makeWindowLeopardStyle(this.getRootPane());
        UnifiedToolBar toolBar = new UnifiedToolBar();
        add(toolBar.getComponent(), BorderLayout.NORTH);
        URL urlSmallIcon = getClass().getClassLoader().getResource("kev-logo-full.png");
        final ImageIcon smallIcon = new ImageIcon(urlSmallIcon);
        this.setIconImage(smallIcon.getImage());
        URL urlIcon = getClass().getClassLoader().getResource("kevoree-logo-full.png");
        ImageIcon topIIcon = new ImageIcon(urlIcon);
        JLabel topImage = new JLabel(topIIcon);
        topImage.setOpaque(false);
        toolBar.addComponentToLeft(topImage);
        setVisible(true);
        setPreferredSize(new Dimension(1024, 768));
        setSize(getPreferredSize());

        final ConsoleShell shell = new ConsoleShell();
        singleton.add(shell, BorderLayout.CENTER);
        singleton.pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        KevoreeKernel kernel = new KevoreeMicroKernelImpl();
        String version = System.getProperty("version");
        if (version == null) {
            SortedSet<String> sets = kernel.getResolver().listVersion("org.kevoree", "org.kevoree.bootstrap", "jar", kernel.getSnapshotURLS());
            for (String s : sets) {
                if (!s.toLowerCase().contains("snapshot")) {
                    version = s;
                    break;
                }
            }
        }
        System.setProperty("node.name", nodeName);
        System.setProperty("node.script", createBootstrapScript(nodeName, groupName, hostName, version));
        String bootJar = "mvn:org.kevoree:org.kevoree.bootstrap:" + version;
        final FlexyClassLoader bootstrapKCL = kernel.install(bootJar, bootJar);
        kernel.boot(bootstrapKCL.getResourceAsStream("KEV-INF/bootinfo"));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            dispose();
                            shell.restore();
                            System.setSecurityManager(null);
                            Runtime.getRuntime().exit(0);
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }

    private static String createBootstrapScript(String nodeName, String groupName, String hostName, String version) {
        StringBuilder buffer = new StringBuilder();
        String versionRequest;
        if (version.toLowerCase().contains("snapshot")) {
            buffer.append("repo \"https://oss.sonatype.org/content/groups/public/\"\n");
            versionRequest = "latest";
        } else {
            buffer.append("repo \"http://repo1.maven.org/maven2/\"\n");
            versionRequest = "release";
        }
        buffer.append("include mvn:org.kevoree.library.java:org.kevoree.library.java.javaNode:");
        buffer.append(versionRequest);
        buffer.append("\n");
        buffer.append("include mvn:org.kevoree.library.java:org.kevoree.library.java.editor:");
        buffer.append(versionRequest);
        buffer.append("\n");
        buffer.append("include mvn:org.kevoree.library.java:org.kevoree.library.java.ws:");
        buffer.append(versionRequest);
        buffer.append("\n");
        if (hostName != null) {
            buffer.append("include mvn:org.kevoree.library.java:org.kevoree.library.java.mqtt:");
            buffer.append(versionRequest);
            buffer.append("\n");
        }
        buffer.append("add " + nodeName + " : org.kevoree.library.JavaNode\n");
        buffer.append("add sync : org.kevoree.library.WSGroup\n");
        buffer.append("attach " + nodeName + " sync\n");
        int groupPort = FreeSocketDetector.detect(9000, 9999);
        int editorPort = FreeSocketDetector.detect(3042, 3300);
        buffer.append("set sync.port/" + nodeName + " = \"" + groupPort + "\"\n");
        buffer.append("add " + nodeName + ".editor : org.kevoree.library.WebEditor\n");
        buffer.append("set " + nodeName + ".editor.port = \"" + editorPort + "\"\n");
        if (hostName != null && !hostName.isEmpty()) {
            buffer.append("add " + groupName + " : org.kevoree.library.MQTTGroup\n");
            buffer.append("attach " + nodeName + " " + groupName + "\n");
            buffer.append("set " + groupName + ".broker = \"" + hostName + "\"");
        }
        return buffer.toString();
    }


}
