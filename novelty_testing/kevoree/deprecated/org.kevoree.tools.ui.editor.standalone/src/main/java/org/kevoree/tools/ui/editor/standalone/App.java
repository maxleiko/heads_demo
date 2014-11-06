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
package org.kevoree.tools.ui.editor.standalone;

import com.explodingpixels.macwidgets.*;
import org.kevoree.KevoreeFactory;
import org.kevoree.impl.DefaultKevoreeFactory;
import org.kevoree.tools.ui.editor.KevoreeEditor;
import org.kevoree.tools.ui.editor.UIEventHandler;
import org.kevoree.tools.ui.editor.command.Command;
import org.kevoree.tools.ui.editor.command.LoadContinuousRemoteModelUICommand;
import org.kevoree.tools.ui.editor.kloud.MiniKloudForm;

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
    static LocalKevsShell kevsPanel = new LocalKevsShell();

    public static void main(final String[] args) throws Exception {

        System.setSecurityManager(null);


        //   System.setProperty("apple.awt.graphics.UseQuartz","true");
        //System.setProperty("sun.java2d.opengl","true");

        // System.setProperty("apple.awt.graphics.UseOpenGL","true");

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                KevoreeFactory kevoreeFactory = new DefaultKevoreeFactory();
                final KevoreeEditor artpanel = new KevoreeEditor();
                kevsPanel.setKernel(artpanel.getPanel().getKernel());
                String frameName = "Kevoree Editor - " + kevoreeFactory.getVersion();

                JFrame jframe = new JFrame(frameName);
                MacUtils.makeWindowLeopardStyle(jframe.getRootPane());
                UnifiedToolBar toolBar = new UnifiedToolBar();
                org.kevoree.tools.ui.editor.ErrorPanel.setTopPanel(toolBar);

                // JButton button = new JButton("Toogle console");
                // button.putClientProperty("JButton.buttonType", "textured");

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
                AbstractButton toogleTypeEditionMode = null;
                try {
                    java.net.URL url = App.class.getClassLoader().getResource("package.png");
                    ImageIcon icon = new ImageIcon(url);
                    toogleTypeEditionMode = MacButtonFactory.makeUnifiedToolBarButton(new JButton("TypeMode", icon));
                    toogleTypeEditionMode.setEnabled(false);
                    toolBar.addComponentToLeft(toogleTypeEditionMode);
                } catch (Exception e) {
                    e.printStackTrace();
                }

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
                }
                /*
                AbstractButton toogleKloud = null;
                try {
                    java.net.URL url = App.class.getClassLoader().getResource("kloud.png");
                    ImageIcon icon = new ImageIcon(url);
                    toogleKloud =
                            MacButtonFactory.makeUnifiedToolBarButton(
                                    new JButton("Kloud", icon));
                    toogleKloud.setEnabled(false);
                    toolBar.addComponentToRight(toogleKloud);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                  */
                AbstractButton toogleMiniKloud = null;
                try {
                    java.net.URL url = App.class.getClassLoader().getResource("kloud.png");
                    ImageIcon icon = new ImageIcon(url);
                    toogleMiniKloud =
                            MacButtonFactory.makeUnifiedToolBarButton(
                                    new JButton("MiniKloud", icon));
                    toogleMiniKloud.setEnabled(false);
                    toolBar.addComponentToRight(toogleMiniKloud);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                jframe.add(toolBar.getComponent(), BorderLayout.NORTH);
                toolBar.installWindowDraggerOnWindow(jframe);
                toolBar.disableBackgroundPainter();

                jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jframe.setPreferredSize(new Dimension(800, 600));

                jframe.setJMenuBar(artpanel.getMenuBar());

                // jframe.add(artpanel.getPanel(), BorderLayout.CENTER);


                // jframe.add(new LogPanel(), BorderLayout.SOUTH);

                /*
                                  String layoutDef =
                                          "(COLUMN (LEAF name=center weight=0.95) (LEAF name=bottom weight=0.05))";
                                  MultiSplitLayout.Node modelRoot =
                                          MultiSplitLayout.parseModel(layoutDef);
                                  JXMultiSplitPane multiSplitPane = new JXMultiSplitPane();
                                  multiSplitPane.getMultiSplitLayout().setModel(modelRoot);

                                  multiSplitPane.add(artpanel.getPanel(), "center");
                                  multiSplitPane.add(new LogPanel(), "bottom");
                                    */

                final LogPanel logPanel = new LogPanel();

                final JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                        artpanel.getPanel(), logPanel);
                //splitPane.setResizeWeight(0.3);
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
                final javax.swing.JLabel labelBot = MacWidgetFactory.createEmphasizedLabel("");
                bottomBar.addComponentToLeft(labelBot);

                UIEventHandler.addCommand(new Command() {
                    @Override
                    public void execute(Object p) {
                        labelBot.setText(p.toString());
                        // labelBot.setFont(new Font("Serif", Font.PLAIN, 10));
                    }
                });


                /*
        JProgressBar jbar = new  JProgressBar(0, 100);
        jbar.setSize(250, 10);
        jbar.setValue(0);
        jbar.setVisible(true);
        jbar.setStringPainted(true);
        jbar.setString("Starting test run");

        jbar.setIndeterminate(true);
        bottomBar.addComponentToLeft(jbar);
                */
                //bottomBar.addComponentToCenter(MacWidgetFactory.createEmphasizedLabel("Kevoree Model"));
                jframe.add(bottomBar.getComponent(), BorderLayout.SOUTH);
                bottomBar.installWindowDraggerOnWindow(jframe);


                jframe.pack();
                jframe.setVisible(true);


                assert toogleConsole != null;
                final AbstractButton finalToogleConsole = toogleConsole;
                assert toogleKevScriptEditor != null;
                final AbstractButton finalToogleKevScriptEditor = toogleKevScriptEditor;
                final AbstractButton finalToogleErrorPanel = toogleErrorPanel;
                //final AbstractButton finalToggleKloudDialog = toogleKloud;
                final AbstractButton finalToggleMiniKloudDialog = toogleMiniKloud;

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
                            //LocalKevsShell kevsPanel = new LocalKevsShell();

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
                final AbstractButton finalToogleTypeEditionMode = toogleTypeEditionMode;
                toogleTypeEditionMode.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        finalToogleTypeEditionMode.setEnabled(!finalToogleTypeEditionMode.isEnabled());
                        if (finalToogleTypeEditionMode.isEnabled()) {
                            artpanel.getPanel().setTypeEditor();
                        } else {
                            artpanel.getPanel().unsetTypeEditor();
                        }
                        p.repaint();
                        p.revalidate();
                    }
                });

                  /*
                final KloudForm kloudForm = new KloudForm(artpanel, finalToggleKloudDialog);
                toogleKloud.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Display the Dialog to configure the kloud and send the model on it
                        finalToggleKloudDialog.setEnabled(!finalToggleKloudDialog.isEnabled());
                        if (finalToggleKloudDialog.isEnabled()) {
                            // display the Dialog
                            kloudForm.display();
                        } else {
                            // hide the Dialog
                            kloudForm.hide();
                        }
                    }
                }); */

                final MiniKloudForm minikloudForm = new MiniKloudForm(artpanel, finalToggleMiniKloudDialog);
                toogleMiniKloud.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Display the Dialog to configure the kloud and send the model on it
                        boolean toggle = !finalToggleMiniKloudDialog.isEnabled();
                        if (toggle) {
                            /*if (*/
                            minikloudForm.startMiniCloud();/*) {
                                finalToggleMiniKloudDialog.setEnabled(toggle);
                            }*/
                        } else {
                            /*if (*/
                            minikloudForm.shutdownMiniCloud();/*) {
                                finalToggleMiniKloudDialog.setEnabled(toggle);
                            }*/
                        }
                    }
                });

                final LoadContinuousRemoteModelUICommand cmdLMORemote2 = new LoadContinuousRemoteModelUICommand();
                final AbstractButton finalToogleSync = toogleSync;
                final AbstractButton finalToogleSyncSend = toogleSyncSend;
                cmdLMORemote2.setKernel(artpanel.getPanel().getKernel());
                toogleSync.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (finalToogleSync.isEnabled()) {
                            cmdLMORemote2.close();
                            finalToogleSync.setEnabled(false);
                            finalToogleSyncSend.setEnabled(false);
                        } else {
                            cmdLMORemote2.execute(null);
                            finalToogleSync.setEnabled(true);
                            finalToogleSyncSend.setEnabled(true);
                        }
                    }
                });


                artpanel.getPanel().getKernel().getModelHandler().addListenerCommand(new Command(){

                    @Override
                    public void execute(Object p) {
                        if (finalToogleSyncSend.isEnabled() && !cmdLMORemote2.currentUpdate()) {
                            cmdLMORemote2.send();
                        }
                    }
                });


                finalToogleSyncSend.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (finalToogleSyncSend.isEnabled()) {
                            cmdLMORemote2.send();
                        }
                    }
                });


                dividerPos = splitPane.getDividerLocation();
            }
        });


    }
}
