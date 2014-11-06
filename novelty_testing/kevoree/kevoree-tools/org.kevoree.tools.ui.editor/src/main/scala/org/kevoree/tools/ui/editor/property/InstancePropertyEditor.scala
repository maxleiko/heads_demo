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
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kevoree.tools.ui.editor.property

import org.kevoree._
import com.explodingpixels.macwidgets.plaf.{HudTextFieldUI, HudLabelUI}
import javax.swing._
import event.{DocumentEvent, DocumentListener}
import java.awt.Dimension
import java.awt.event.{ActionListener, ActionEvent}
import text.BadLocationException
import org.slf4j.LoggerFactory
import com.explodingpixels.macwidgets.IAppWidgetFactory
import tools.ui.editor.{ModelHelper, KevoreeUIKernel, UIHelper}
import scala.collection.JavaConversions._
import java.util


/**
 * Created by IntelliJ IDEA.
 * User: duke
 * Date: 12/10/11
 * Time: 10:02
 * To change this template use File | Settings | File Templates.
 */

class InstancePropertyEditor(elem: org.kevoree.Instance, kernel: KevoreeUIKernel) extends NamedElementPropertyEditor(elem, kernel) {

  val logger = LoggerFactory.getLogger(this.getClass)


  def getValue(instance: Instance, att: DictionaryAttribute, targetNode: Option[String]): String = {
    targetNode match {
      case Some(targetNodeSearch) => {
        val fragDico = instance.findFragmentDictionaryByID(targetNodeSearch)
        if (fragDico != null) {
          val v = fragDico.findValuesByID(att.getName)
          if (v != null) {
            return v.getValue
          }
        }
        if (instance.getDictionary != null) {
          val v = instance.getDictionary.findValuesByID(att.getName)
          if (v != null) {
            return v.getValue
          }
        }
      }
      case None => {
        if (instance.getDictionary != null) {
          val v = instance.getDictionary.findValuesByID(att.getName)
          if (v != null) {
            return v.getValue
          }
        }
      }
    }
    return att.getDefaultValue //DEFAULT CASE RETURN EMPTY VALUE
  }

  def setValue(aValue: AnyRef, instance: Instance, att: DictionaryAttribute, targetNode: Option[String]): Unit = {
    var value: Value = null
    targetNode match {
      case Some(targetNodeSearch) => {
        val fragDico = instance.findFragmentDictionaryByID(targetNodeSearch)
        if (fragDico != null) {
          val v = fragDico.findValuesByID(att.getName)
          if (v != null) {
            value = v
          }
        }
        if (instance.getDictionary != null) {
          val v = instance.getDictionary.findValuesByID(att.getName)
          if (v != null) {
            value = v
          }
        }
      }
      case None => {
        if (instance.getDictionary != null) {
          val v = instance.getDictionary.findValuesByID(att.getName)
          if (v != null) {
            value = v
          }
        }
      }
    }
    if (value == null) {
      value = ModelHelper.kevoreeFactory.createValue
      value.setName(att.getName)
      targetNode match {
        case None => {
          if (instance.getDictionary == null) {
            val dico = ModelHelper.kevoreeFactory.createDictionary()
            instance.setDictionary(dico)
          }
          instance.getDictionary.addValues(value)
        }
        case Some(tNode) => {
          var fragDico = instance.findFragmentDictionaryByID(tNode)
          if(fragDico == null){
            fragDico = ModelHelper.kevoreeFactory.createFragmentDictionary()
            fragDico.setName(tNode)
            instance.addFragmentDictionary(fragDico)
          }
          fragDico.addValues(value)
        }
      }
    }
    value.setValue(aValue.toString)
    kernel.getModelHandler.notifyChanged()
  }

  //CONSTRUCTOR CODE
  if (elem.getDictionary == null) {
    elem.setDictionary(ModelHelper.kevoreeFactory.createDictionary)
  }
  var p: JPanel = new JPanel(new SpringLayout)
  p.setBorder(null)

  var nbLigne = 0
  if (elem.getTypeDefinition.getDictionaryType != null) {
    for (att <- elem.getTypeDefinition.getDictionaryType.getAttributes) {
      att.getDatatype match {
        /*
      case _ if (att.getDatatype != "" && att.getDatatype.startsWith("enum=") && !att.getFragmentDependant) => {
        val l: JLabel = new JLabel(att.getName, SwingConstants.TRAILING)
        l.setUI(new HudLabelUI)
        p.add(l)
        p.add(getEnumBox(att, l, None))
        nbLigne = nbLigne + 1
      }
      case _ if (att.getDatatype != "" && att.getDatatype.startsWith("enum=") && att.getFragmentDependant) => {
        getNodesLinked(elem).foreach {
          nodeName =>
            val l: JLabel = new JLabel(att.getName + "->" + nodeName, SwingConstants.TRAILING)
            l.setUI(new HudLabelUI)
            p.add(l)
            p.add(getEnumBox(att, l, Some(nodeName)))
            nbLigne = nbLigne + 1
        }
      }  */


        case _ if (att.getFragmentDependant) => {

          getNodesLinked(elem).foreach {
            nodeName =>
              val l: JLabel = new JLabel(att.getName + "->" + nodeName, SwingConstants.TRAILING)
              l.setUI(new HudLabelUI)
              p.add(l)
              p.add(getTextField(att, l, Some(nodeName)))
              nbLigne = nbLigne + 1
          }
        }
        case _ => {


          val l: JLabel = new JLabel(att.getName, SwingConstants.TRAILING)
          l.setUI(new HudLabelUI)
          p.add(l)
          p.add(getTextField(att, l, None))
          nbLigne = nbLigne + 1
        }
      }
    }
    SpringUtilities.makeCompactGrid(p, nbLigne, 2, 6, 6, 6, 6)
  }


  p.setOpaque(false)
  var scrollPane: JScrollPane = new JScrollPane(p)
  scrollPane.getViewport.setOpaque(false)
  scrollPane.setOpaque(false)
  scrollPane.setBorder(null)
  scrollPane.setPreferredSize(new Dimension(250, 150))

  IAppWidgetFactory.makeIAppScrollPane(scrollPane)
  this.addCenter(scrollPane)

  //END CONSTRUCTOR CODE


  def getNodesLinked(i: Instance): List[String] = {
    i match {
      case g: Group => {
        g.getSubNodes.map(s => s.getName).toList
      }
      case c: Channel => {
        val nodeNames = new util.HashSet[String]()
        c.getBindings.foreach {
          mb =>
            if (mb.getPort != null) {
              val node = mb.getPort.eContainer().eContainer().asInstanceOf[NamedElement]
              nodeNames.add(node.getName)
            }
        }
        nodeNames.toList
      }
      case _ => List()
    }
  }


  def getBooleanBox(att: DictionaryAttribute, label: JLabel, targetNode: Option[String]): JComponent = {
    val model = new DefaultComboBoxModel
    UIHelper.addItem(model, "true")
    UIHelper.addItem(model, "false")
    val comboBox = UIHelper.createJComboBox(model)
    label.setLabelFor(comboBox)
    p.add(comboBox)
    UIHelper.setSelectedItem(comboBox, (getValue(elem, att, targetNode)))
    comboBox.asInstanceOf[ {def addActionListener(l: ActionListener)}].addActionListener(new ActionListener {
      def actionPerformed(actionEvent: ActionEvent): Unit = {
        setValue(UIHelper.getSelectedItem(comboBox).toString, elem, att, targetNode)
      }
    })
    comboBox
  }


  /*
  def getEnumBox(att: DictionaryAttribute, label: JLabel, targetNode: Option[String]): JComponent = {
    val values: String = att.getDatatype.replaceFirst("enum=", "")
    val model = new DefaultComboBoxModel
    values.split(",").foreach {
      value => UIHelper.addItem(model, value)
    }
    val comboBox = UIHelper.createJComboBox(model)
    label.setLabelFor(comboBox)
    p.add(comboBox)
    UIHelper.setSelectedItem(comboBox, (getValue(elem, att, targetNode)))
    comboBox.asInstanceOf[ {def addActionListener(l: ActionListener)}].addActionListener(new ActionListener {
      def actionPerformed(actionEvent: ActionEvent): Unit = {
        setValue(UIHelper.getSelectedItem(comboBox).toString, elem, att, targetNode)
      }
    })
    comboBox
  }*/


  //get Default TextField
  def getTextField(att: DictionaryAttribute, label: JLabel, targetNode: Option[String]): JComponent = {
    val textField: JTextField = new JTextField(10)
    textField.setUI(new HudTextFieldUI)
    textField.getDocument.addDocumentListener(new DocumentListener {
      def insertUpdate(documentEvent: DocumentEvent): Unit = {
        try {
          setValue(documentEvent.getDocument.getText(0, documentEvent.getDocument.getLength), elem, att, targetNode)
        }
        catch {
          case e: BadLocationException => {
            e.printStackTrace
          }
        }
      }

      def removeUpdate(documentEvent: DocumentEvent): Unit = {
        try {
          setValue(documentEvent.getDocument.getText(0, documentEvent.getDocument.getLength), elem, att, targetNode)
        }
        catch {
          case e: BadLocationException => {
            e.printStackTrace
          }
        }
      }

      def changedUpdate(documentEvent: DocumentEvent): Unit = {
        try {
          setValue(documentEvent.getDocument.getText(0, documentEvent.getDocument.getLength), elem, att, targetNode)
        }
        catch {
          case e: BadLocationException => {
            e.printStackTrace
          }
        }
      }
    })
    label.setLabelFor(textField)
    textField.setText(getValue(elem, att, targetNode))
    textField
  }


}