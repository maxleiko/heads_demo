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

package org.kevoree.tools.ui.editor;

import javax.swing.*;

import org.kevoree.ContainerRoot;
import org.kevoree.tools.ui.editor.command.LoadModelCommand;
import org.kevoree.tools.ui.editor.command.SaveActuelModelCommand;
import org.kevoree.tools.ui.editor.panel.KevoreeEditorPanel;

/**
 *
 * @author ffouquet
 */
public class KevoreeEditor {

    private KevoreeEditorPanel panel = null;
    private KevoreeMenuBar menubar = null;

    public KevoreeEditor(){
        panel = new KevoreeEditorPanel();
        menubar = new KevoreeMenuBar(panel.getKernel());
    }

    public KevoreeEditorPanel getPanel(){
        return panel;
    }

    public JMenuBar getMenuBar(){
        return menubar;
    }

    public void loadModel(String uri){
        LoadModelCommand command = new LoadModelCommand();
        command.setKernel(panel.getKernel());
        command.execute(uri);
    }

    public void loadModelFromObject(ContainerRoot obj){
        LoadModelCommand command = new LoadModelCommand();
        command.setKernel(panel.getKernel());
        command.execute(obj);
    }

    public void loadLib(){
        //TODO
    }


    public void setDefaultSaveLocation(String url){
        SaveActuelModelCommand.setDefaultLocation(url);
    }



}
