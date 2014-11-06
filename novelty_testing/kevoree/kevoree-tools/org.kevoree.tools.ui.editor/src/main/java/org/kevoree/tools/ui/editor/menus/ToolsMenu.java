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
package org.kevoree.tools.ui.editor.menus;/*
* Author : Gregory Nain (developer.name@uni.lu)
* Date : 08/11/12
* (c) 2012 University of Luxembourg – Interdisciplinary Centre for Security Reliability and Trust (SnT)
* All rights reserved
*/

import org.kevoree.log.Log;
import org.kevoree.tools.ui.editor.KevoreeUIKernel;
import org.kevoree.tools.ui.editor.command.DisplayModelTextEditor;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolsMenu extends JMenu {

    private KevoreeUIKernel kernel;

    public ToolsMenu(KevoreeUIKernel kernel) {
        super("Tools");
        this.kernel = kernel;
        add(createSourceEditorItem());
        add(createLoggerItem());
    }

    private JMenuItem createSourceEditorItem() {
        JMenuItem sourceEditorItem = new JMenuItem("Source editor");
        DisplayModelTextEditor sourceEditorCmd = new DisplayModelTextEditor();
        sourceEditorCmd.setKernel(kernel);
        sourceEditorItem.addActionListener(new CommandActionListener(sourceEditorCmd));
        return sourceEditorItem;
    }

    private JMenu createLoggerItem() {
        JMenu loggerMenu = new JMenu("Logger");
        ButtonGroup logGroup = new ButtonGroup();
        JMenuItem warn = createLoggerWarnItem();
        JMenuItem debug = createLoggerDebugItem();
        JMenuItem info = createLoggerInfoItem();

        logGroup.add(warn);
        logGroup.add(debug);
        logGroup.add(info);

        loggerMenu.add(warn);
        loggerMenu.add(debug);
        loggerMenu.add(info);

        return loggerMenu;
    }


    private JMenuItem createLoggerWarnItem() {
        JCheckBoxMenuItem warnLevelItem = new JCheckBoxMenuItem("Warn");
        warnLevelItem.setSelected(true);
        warnLevelItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Log.set(Log.LEVEL_WARN);
            }
        });
        return warnLevelItem;

    }

    private JMenuItem createLoggerDebugItem() {
        JCheckBoxMenuItem debugLevelItem = new JCheckBoxMenuItem("Debug");
        debugLevelItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Log.set(Log.LEVEL_DEBUG);
            }
        });
        return debugLevelItem;
    }

    private JMenuItem createLoggerInfoItem() {

        JCheckBoxMenuItem infoLevelItem = new JCheckBoxMenuItem("Info");
        infoLevelItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Log.set(Log.LEVEL_INFO);
            }
        });

        return infoLevelItem;

    }


}
