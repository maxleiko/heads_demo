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
package org.kevoree.tools.ui.framework;

import com.explodingpixels.macwidgets.HudWidgetFactory;
import com.explodingpixels.macwidgets.HudWindow;
import com.explodingpixels.macwidgets.IAppWidgetFactory;
import org.kevoree.tools.ui.framework.elements.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

import org.kevoree.tools.ui.framework.elements.PortPanel.PortType;

/**
 * @author ffouquet
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {




        JFrame jframe = new JFrame("Art2 UI Tester");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setPreferredSize(new Dimension(800, 600));


        ModelPanel model = new ModelPanel();

        //Test trianflePanel
        IconPanel panel = new IconPanel("1349960253_triangle.png");
        panel.setTitle("[3..*]");
        model.addNode(panel);



        //Test Group Panel
        GroupPanel groupPanel = new GroupPanel();
        groupPanel.setTitle("G1");
        model.addGroup(groupPanel);


        NodePanel node1 = new NodePanel();
        NodePanel node2 = new NodePanel();
        model.addNode(node2);
        model.addNode(node1);

        NodePanel childNode = new NodePanel();
        childNode.setTitle("ChildNode");
        node1.add(childNode);
        ComponentPanel childC1 = new ComponentPanel();
        childC1.setTypeName("childSub");
        childNode.add(childC1);

        ChannelPanel hub1 = new ChannelPanel();
        hub1.setTitle("topic1");
        model.addHub(hub1);

        ComponentPanel c1 = new ComponentPanel();
        c1.setTypeName("MyTpe1");

        ComponentPanel c2 = new ComponentPanel();
        ComponentPanel c3 = new ComponentPanel();
        ComponentPanel c4 = new ComponentPanel();

        PortPanel p11 = new PortPanel();
        p11.setType(PortType.PROVIDED);
        p11.setNature(PortPanel.PortNature.SERVICE);

        PortPanel p12 = new PortPanel();
        p12.setType(PortType.REQUIRED);
        p12.setNature(PortPanel.PortNature.SERVICE);
        PortPanel p21 = new PortPanel();
        p21.setType(PortType.PROVIDED);
        p21.setNature(PortPanel.PortNature.SERVICE);
        PortPanel p22 = new PortPanel();
        p22.setType(PortType.REQUIRED);
        p22.setNature(PortPanel.PortNature.SERVICE);
        PortPanel p23 = new PortPanel();
        p23.setType(PortType.REQUIRED);
        p23.setNature(PortPanel.PortNature.SERVICE);
        PortPanel p24 = new PortPanel();
        p24.setType(PortType.REQUIRED);
        p24.setNature(PortPanel.PortNature.MESSAGE);


        p11.setTitle("P11");
        p12.setTitle("P12");
        p21.setTitle("P21");
        p22.setTitle("P22");
        p23.setTitle("P23");
        p24.setTitle("P24");


        Binding b = new Binding(Binding.Type.input);
        b.setSelected(false);
        b.setFrom(p11);
        b.setTo(p22);
        model.addBinding(b);

        Binding sb = new Binding(Binding.Type.input);
        sb.setSelected(false);
        sb.setFrom(p12);
        sb.setTo(p23);
        model.addBinding(sb);

        Binding mb = new Binding(Binding.Type.ouput);
        mb.setSelected(false);
        mb.setFrom(p24);
        mb.setTo(hub1);
        model.addBinding(mb);

        c1.setTitle("c1");
        c1.addLeft(p11);
        c1.addRight(p12);
        c2.setTitle("c2");
        c2.addLeft(p21);
        c2.addRight(p22);
        c2.addRight(p23);
        c2.addRight(p24);

        node1.setTitle("node1");
        node1.setTitle("myNode", "ArduinoNode");


        node1.add(c1);
        node1.add(c2);
        node2.add(c3);
        node2.add(c4);


      //  ZoomPanel zpanel = new ZoomPanel();
       // zpanel.setLayout(new BorderLayout());
       // zpanel.add(model, BorderLayout.CENTER);
      //  zpanel.changeZoom(1);


        JScrollPane scrollPane = new JScrollPane(model, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        IAppWidgetFactory.makeIAppScrollPane(scrollPane);


        EditableModelPanel epanel = new EditableModelPanel(scrollPane);


        //jframe.add(epanel, BorderLayout.CENTER);


        jframe.add(epanel, BorderLayout.CENTER);


        JPanel prop = new JPanel();
        prop.add(new JButton("TOTO"));

       // epanel.displayProperties(prop);


        jframe.pack();
        jframe.setVisible(true);


        HudWindow hud = new HudWindow("Window");
        hud.getJDialog().setSize(300, 350);
        hud.getJDialog().setLocationRelativeTo(jframe);
        hud.getJDialog().setResizable(true);
        hud.getJDialog().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       // hud.getJDialog().setVisible(true);

        JTextField textField = HudWidgetFactory.createHudTextField("Text field");
         hud.getJDialog().add(textField);




    }
}
