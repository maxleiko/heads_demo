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
package org.kevoree.tools.ui.editor.command

import org.kevoree.tools.ui.editor.aspects.Art2UIAspects._
import org.kevoree.tools.ui.editor.KevoreeUIKernel
import org.kevoree._

class RemoveInstanceCommand(elem: org.kevoree.NamedElement) extends Command {

  var kernel: KevoreeUIKernel = null

  def execute(p: Object) {

    elem match {
      case inst: Channel => {
        inst.removeModelAndUI(kernel);
        updateType(inst)
      }
      case inst: ComponentInstance => {
        inst.removeModelAndUI(kernel);
        updateType(inst)
      }
      case inst: ContainerNode => {
        import scala.collection.JavaConversions._
        inst.getHosts.foreach {
          hn =>
            val c = new RemoveInstanceCommand(hn)
            c.kernel = kernel
            c.execute(null)
        }

        inst.removeModelAndUI(kernel);
        updateType(inst)
      }
      case inst: Group => {
        inst.removeModelAndUI(kernel);
        updateType(inst)
      }
    }
    if(elem.isInstanceOf[Instance]){
      elem.asInstanceOf[Instance].delete()
    }
    kernel.getEditorPanel.unshowPropertyEditor()
  }

  def updateType(i: Instance) {
    kernel.getEditorPanel.getPalette.updateAllValue()
    kernel.getModelHandler.ModelListener.notifyChanged()
  }


}
