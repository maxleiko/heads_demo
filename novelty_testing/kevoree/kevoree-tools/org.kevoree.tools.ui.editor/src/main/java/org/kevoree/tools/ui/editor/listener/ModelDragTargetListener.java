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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevoree.tools.ui.editor.listener;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.*;
import java.util.*;

import org.kevoree.ContainerNode;
import org.kevoree.tools.ui.editor.KevoreeUIKernel;
import org.kevoree.tools.ui.editor.command.*;
import org.kevoree.tools.ui.framework.elements.*;

import java.awt.dnd.*;

import org.slf4j.*;
import scala.io.Source;

/**
 * implementation of the target listener
 *
 * @author francoisfouquet
 */
public class ModelDragTargetListener extends DropTarget {

    KevoreeUIKernel kernel;
    ModelPanel target;

    DataFlavor mimeDataFlavour;
    DataFlavor urlDataFlavour;
    DataFlavor uriStringDataFlavour;

    Logger logger = LoggerFactory.getLogger(ModelDragTargetListener.class.getName());

    /**
     * constructor
     *
     * @param _kernel the table view panel
     * @param _target the view of the component
     */
    public ModelDragTargetListener(ModelPanel _target, KevoreeUIKernel _kernel) {
        kernel = _kernel;
        target = _target;
        try {
            mimeDataFlavour = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType);
            urlDataFlavour = new DataFlavor("application/x-java-url;class=java.net.URL");
            uriStringDataFlavour = new DataFlavor("text/uri-list;class=java.lang.String");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Boolean isDropAccept(Object o) {
        if (o instanceof ChannelTypePanel) {
            return true;
        }
        if (o instanceof GroupTypePanel) {
            return true;
        }
        if (o instanceof NodeTypePanel) {
            return true;
        }
        if (o instanceof NodePanel) {
            //Only if accepted if node was previously in another node
            for (ContainerNode node : kernel.getModelHandler().getActualModel().getNodes()) {
                ContainerNode flightNode =(ContainerNode) kernel.getUifactory().getMapping().get(o);
                if (node.getHosts().contains(flightNode)) {
                    return true;
                }
            }
            return false;
        }
        //otherwise return false / no other type accepted
        return false;
    }

    /**
     * callback when DnD is finished
     *
     * @param arg0
     */
    @Override
    public void drop(DropTargetDropEvent arg0) {
        try {
            if (arg0.getTransferable().isDataFlavorSupported(mimeDataFlavour)) {
                Object o = arg0.getTransferable().getTransferData(mimeDataFlavour);
                if (isDropAccept(o)) {
                    if (o instanceof ChannelTypePanel) {
                        AddChannelCommand command = new AddChannelCommand();
                        command.setPoint(arg0.getLocation());
                        command.setKernel(kernel);
                        command.execute(o);
                    }
                    if (o instanceof NodeTypePanel) {
                        AddNodeCommand command = new AddNodeCommand();
                        command.setPoint(arg0.getLocation());
                        command.setKernel(kernel);
                        command.execute(o);
                    } else {
                        if ((o instanceof NodePanel)) {
                            NodePanel newnodepanel = (NodePanel) o;
                            kernel.getModelPanel().addNode(newnodepanel);
                            for (ContainerNode node : kernel.getModelHandler().getActualModel().getNodes()) {
                                if (node.getHosts().contains((ContainerNode) kernel.getUifactory().getMapping().get(newnodepanel))) {
                                    node.removeHosts((ContainerNode) kernel.getUifactory().getMapping().get(newnodepanel));
                                }
                            }
                            newnodepanel.setLocation((int) (arg0.getLocation().x - newnodepanel.getPreferredSize().getWidth() / 2), (int) (arg0.getLocation().y - newnodepanel.getPreferredSize().getHeight() / 2));
                            DragSource dragSource = DragSource.getDefaultDragSource();
                            for (DragSourceMotionListener li : dragSource.getDragSourceMotionListeners()) {
                                if (li instanceof org.kevoree.tools.ui.editor.listener.NodeDragSourceListener) {
                                    if (((NodeDragSourceListener) li).flightNode.equals(newnodepanel)) {
                                        try {
                                            dragSource.removeDragSourceListener((DragSourceListener) li);
                                            dragSource.removeDragSourceMotionListener((DragSourceMotionListener) li);
                                            ((NodeDragSourceListener)li).dgr.resetRecognizer();
                                            ((NodeDragSourceListener)li).dgr.removeDragGestureListener((DragGestureListener) li);
                                            newnodepanel.removeMouseListener((MouseListener)((NodeDragSourceListener)li).dgr);
                                        } catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                            kernel.getModelHandler().notifyChanged();
                        }
                    }
                    if (o instanceof GroupTypePanel) {
                        AddGroupCommand command = new AddGroupCommand();
                        command.setPoint(arg0.getLocation());
                        command.setKernel(kernel);
                        command.execute(o);
                    }
                    kernel.getModelPanel().repaint();
                    kernel.getModelPanel().revalidate();
                    arg0.dropComplete(true);
                } else {
                    arg0.rejectDrop();
                }
            } else if (arg0.getTransferable().isDataFlavorSupported(uriStringDataFlavour)) {
                logger.debug("Drop accepted with URL flavour.");
                arg0.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);//Necessary because of drop from an external source
                Object url = arg0.getTransferable().getTransferData(uriStringDataFlavour);
                logger.debug("Url: " + url.toString().trim());
                URI uri = new URI(url.toString().trim());
                logger.debug("UriPath: " + uri.getPath());

                if(uri.getPath().endsWith(".kev") || uri.getPath().endsWith(".jar")){
                    LoadNewLibCommand loadLib = new LoadNewLibCommand();
                    loadLib.setKernel(kernel);
                    loadLib.execute(uri.getPath());
                }
                if(uri.getPath().endsWith(".kevs")){
                    KevScriptCommand lcommand = new KevScriptCommand();
                    lcommand.setKernel(kernel);
                    lcommand.execute(Source.fromFile(uri.getPath(), "UTF-8").getLines().mkString("\n"));
                }

                arg0.dropComplete(true);
            } else {
                logger.debug("No matching flavour for this drop.");
                logger.debug("Available Flavours:" + Arrays.toString(arg0.getTransferable().getTransferDataFlavors()));
                arg0.rejectDrop();
            }
        } catch (UnsupportedFlavorException ex) {
            logger.error("Flavour not supported.", ex);
            arg0.rejectDrop();
        } catch (IOException e) {
            logger.error("IOException.", e);
            arg0.rejectDrop();
        } catch (URISyntaxException e) {
            logger.error("URI Exception.", e);
            arg0.rejectDrop();
        }

    }

    /**
     * not implemented
     *
     * @param dtde
     */
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
    }

    /**
     * not implemented
     *
     * @param arg0
     */
    @Override
    public void dragExit(DropTargetEvent arg0) {
    }

    /**
     * not implemented
     *
     * @param arg0
     */
    @Override
    public void dragOver(DropTargetDragEvent arg0) {
        if (kernel.getModelPanel().getFlightObject() != null) {
            kernel.getModelPanel().getFlightObject().setBounds(
                    (int) arg0.getLocation().getX() - (kernel.getModelPanel().getFlightObject().getWidth() / 2),
                    (int) arg0.getLocation().getY() - (kernel.getModelPanel().getFlightObject().getHeight() / 2),
                    kernel.getModelPanel().getFlightObject().getWidth(),
                    kernel.getModelPanel().getFlightObject().getHeight());
            kernel.getModelPanel().repaint();
            //  kernel.getModelPanel().revalidate();
        }
    }

    /**
     * not implemented
     *
     * @param arg0
     */
    @Override
    public void dropActionChanged(DropTargetDragEvent arg0) {
    }
}
