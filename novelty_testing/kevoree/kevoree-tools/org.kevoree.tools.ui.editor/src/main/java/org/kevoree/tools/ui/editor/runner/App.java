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
package org.kevoree.tools.ui.editor.runner;

import com.explodingpixels.macwidgets.*;
import org.kevoree.factory.DefaultKevoreeFactory;
import org.kevoree.factory.KevoreeFactory;
import org.kevoree.tools.ui.editor.ExtKevScriptEditor;
import org.kevoree.tools.ui.editor.KevoreeEditor;
import org.kevoree.tools.ui.editor.LogPanel;
import org.kevoree.tools.ui.editor.UIEventHandler;
import org.kevoree.tools.ui.editor.command.Command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Hello world!
 */
public class App {

    static Boolean consoleShow = false;
    static Boolean kevsShow = false;
    static Boolean errorShow = false;


    static int dividerPos = 0;
    static ExtKevScriptEditor kevsPanel = null;

    public static void main(final String[] args) throws Exception {

        if (System.getProperty("user.home").contains("%userprofile%")) {
            //workaround
            System.setProperty("user.home", "");
        }

        System.setProperty("awt.useSystemAAFontSettings", "lcd");
        System.setProperty("swing.aatext", "true");

        System.setSecurityManager(null);
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                KevoreeFactory kevoreeFactory = new DefaultKevoreeFactory();
                final KevoreeEditor artpanel = new KevoreeEditor();
                kevsPanel = new ExtKevScriptEditor(artpanel.getPanel().getKernel());
                String frameName = "Kevoree Editor - " + kevoreeFactory.getVersion();

                JFrame jframe = new JFrame(frameName);
                MacUtils.makeWindowLeopardStyle(jframe.getRootPane());
                UnifiedToolBar toolBar = new UnifiedToolBar();
                org.kevoree.tools.ui.editor.ErrorPanel.setTopPanel(toolBar);
                AbstractButton toogleConsole = null;
                try {
                    java.net.URL url = App.class.getClassLoader().getResource("terminal.png");
                    ImageIcon icon = new ImageIcon(url);
                    toogleConsole =
                            MacButtonFactory.makeUnifiedToolBarButton(
                                    new JButton("Console", icon));
                    toogleConsole.setEnabled(false);
                    toolBar.addComponentToLeft(toogleConsole);


                } catch (Exception e) {
                    e.printStackTrace();
                }

                AbstractButton toogleErrorPanel = null;
                try {
                    java.net.URL url = App.class.getClassLoader().getResource("status_unknown.png");
                    ImageIcon icon = new ImageIcon(url);
                    toogleErrorPanel =
                            MacButtonFactory.makeUnifiedToolBarButton(
                                    new JButton("Error", icon));
                    toogleErrorPanel.setEnabled(false);
                    toolBar.addComponentToLeft(toogleErrorPanel);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                AbstractButton toogleKevScriptEditor = null;
                try {
                    java.net.URL url = App.class.getClassLoader().getResource("runprog.png");
                    ImageIcon icon = new ImageIcon(url);
                    toogleKevScriptEditor =
                            MacButtonFactory.makeUnifiedToolBarButton(
                                    new JButton("KevScript", icon));
                    toogleKevScriptEditor.setEnabled(false);
                    toolBar.addComponentToLeft(toogleKevScriptEditor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /*
                AbstractButton toogleSyncSend = null;
                try {
                    java.net.URL url = App.class.getClassLoader().getResource("1371014427_File Send.png");
                    ImageIcon icon = new ImageIcon(url);
                    toogleSyncSend =
                            MacButtonFactory.makeUnifiedToolBarButton(
                                    new JButton("WSPush", icon));
                    toogleSyncSend.setEnabled(false);
                    toolBar.addComponentToRight(toogleSyncSend);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                AbstractButton toogleSync = null;
                try {
                    java.net.URL url = App.class.getClassLoader().getResource("1371014235_agt_reload.png");
                    ImageIcon icon = new ImageIcon(url);
                    toogleSync =
                            MacButtonFactory.makeUnifiedToolBarButton(
                                    new JButton("WSSync", icon));
                    toogleSync.setEnabled(false);
                    toolBar.addComponentToRight(toogleSync);
                } catch (Exception e) {
                    e.printStackTrace();
                } */
                jframe.add(toolBar.getComponent(), BorderLayout.NORTH);
                toolBar.installWindowDraggerOnWindow(jframe);
                toolBar.disableBackgroundPainter();



               // Properties properties = System.getProperties();
               // properties.list(System.out);

                if (System.getProperty("idea.config.path")!=null) {
                    jframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                } else {
                    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }

                jframe.setPreferredSize(new Dimension(800, 600));
                jframe.setJMenuBar(artpanel.getMenuBar());

                final LogPanel logPanel = new LogPanel();

                final JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                        artpanel.getPanel(), logPanel);
                splitPane.setOneTouchExpandable(true);
                splitPane.setContinuousLayout(true);
                splitPane.setDividerSize(6);
                splitPane.setDividerLocation(200);
                splitPane.setResizeWeight(1.0);
                splitPane.setBorder(null);


                final JPanel p = new JPanel();
                p.setOpaque(false);
                p.setLayout(new BorderLayout());
                p.add(artpanel.getPanel(), BorderLayout.CENTER);
                jframe.add(p, BorderLayout.CENTER);


                final BottomBar bottomBar = new BottomBar(BottomBarSize.EXTRA_SMALL);
                final JLabel labelBot = MacWidgetFactory.createEmphasizedLabel("");
                bottomBar.addComponentToLeft(labelBot);

                UIEventHandler.addCommand(new Command() {
                    @Override
                    public void execute(Object p) {
                        labelBot.setText(p.toString());
                    }
                });

                jframe.add(bottomBar.getComponent(), BorderLayout.SOUTH);
                bottomBar.installWindowDraggerOnWindow(jframe);


                jframe.pack();
                jframe.setVisible(true);


                assert toogleConsole != null;
                final AbstractButton finalToogleConsole = toogleConsole;
                assert toogleKevScriptEditor != null;
                final AbstractButton finalToogleKevScriptEditor = toogleKevScriptEditor;
                final AbstractButton finalToogleErrorPanel = toogleErrorPanel;
                toogleConsole.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {

                        finalToogleConsole.setEnabled(!finalToogleConsole.isEnabled());
                        finalToogleKevScriptEditor.setEnabled(false);
                        finalToogleErrorPanel.setEnabled(false);
                        kevsShow = false;
                        errorShow = false;
                        if (consoleShow) {
                            dividerPos = splitPane.getDividerLocation();
                            p.removeAll();
                            p.add(artpanel.getPanel(), BorderLayout.CENTER);
                            p.repaint();
                            p.revalidate();

                        } else {
                            dividerPos = splitPane.getDividerLocation();
                            p.removeAll();
                            p.add(splitPane, BorderLayout.CENTER);
                            splitPane.setTopComponent(artpanel.getPanel());
                            splitPane.setBottomComponent(logPanel);
                            splitPane.setDividerLocation(dividerPos);
                            p.repaint();
                            p.revalidate();

                        }
                        consoleShow = !consoleShow;
                    }
                });
                toogleKevScriptEditor.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        finalToogleKevScriptEditor.setEnabled(!finalToogleKevScriptEditor.isEnabled());
                        finalToogleConsole.setEnabled(false);
                        finalToogleErrorPanel.setEnabled(false);
                        consoleShow = false;
                        errorShow = false;
                        if (kevsShow) {
                            dividerPos = splitPane.getDividerLocation();
                            p.removeAll();
                            p.add(artpanel.getPanel(), BorderLayout.CENTER);
                            p.repaint();
                            p.revalidate();

                        } else {
                            dividerPos = splitPane.getDividerLocation();
                            p.removeAll();
                            p.add(splitPane, BorderLayout.CENTER);
                            splitPane.setTopComponent(artpanel.getPanel());
                            splitPane.setBottomComponent(kevsPanel);
                            splitPane.setDividerLocation(dividerPos);
                            p.repaint();
                            p.revalidate();

                        }
                        kevsShow = !kevsShow;
                    }
                });
                toogleErrorPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        finalToogleErrorPanel.setEnabled(!finalToogleErrorPanel.isEnabled());
                        finalToogleConsole.setEnabled(false);
                        finalToogleKevScriptEditor.setEnabled(false);
                        consoleShow = false;
                        kevsShow = false;
                        if (errorShow) {
                            dividerPos = splitPane.getDividerLocation();
                            p.removeAll();
                            p.add(artpanel.getPanel(), BorderLayout.CENTER);
                            p.repaint();
                            p.revalidate();
                        } else {
                            dividerPos = splitPane.getDividerLocation();
                            p.removeAll();
                            p.add(splitPane, BorderLayout.CENTER);
                            splitPane.setTopComponent(artpanel.getPanel());
                            splitPane.setBottomComponent(org.kevoree.tools.ui.editor.ErrorPanel.getPanel());
                            splitPane.setDividerLocation(dividerPos);
                            p.repaint();
                            p.revalidate();

                        }
                        errorShow = !errorShow;
                    }
                });


                dividerPos = splitPane.getDividerLocation();
            }
        });


    }
}
