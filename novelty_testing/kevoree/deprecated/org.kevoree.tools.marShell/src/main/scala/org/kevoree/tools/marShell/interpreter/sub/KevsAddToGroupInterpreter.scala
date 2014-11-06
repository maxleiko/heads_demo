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

package org.kevoree.tools.marShell.interpreter.sub

import org.kevoree.tools.marShell.interpreter.KevsAbstractInterpreter
import org.kevoree.tools.marShell.interpreter.KevsInterpreterContext
import org.kevoree.tools.marShell.ast.AddToGroupStatement
import org.kevoree.{ContainerNode, Group}
import scala.collection.JavaConversions._

case class KevsAddToGroupInterpreter(addToGroup: AddToGroupStatement) extends KevsAbstractInterpreter {

  def interpret(context: KevsInterpreterContext): Boolean = {

    var groups: List[Group] = List()
    if (addToGroup.groupName == "*") {
      groups = context.model.getGroups.toList
    } else {
      context.model.findByPath("groups[" + addToGroup.groupName + "]", classOf[Group]) match {
        case g:Group => groups = List(g)
        case null => {
          context.appendInterpretationError("Could not add node '"+addToGroup.nodeName+"' to group '"+addToGroup.groupName+"'. Group not found.")
          //logger.debug("There is no group named {}", addToGroup.groupName)
          false
        }
      }
    }

    var nodes: List[ContainerNode] = List()
    if (addToGroup.nodeName == "*") {
      nodes = context.model.getNodes.toList
    } else {
      context.model.findByPath("nodes[" + addToGroup.nodeName + "]", classOf[ContainerNode]) match {
        case g:ContainerNode => nodes = List(g)
        case null => {
          context.appendInterpretationError("Could not add node '"+addToGroup.nodeName+"' to group '"+addToGroup.groupName+"'. Node not found.")
          //logger.debug("There is no node named {}", addToGroup.nodeName)
          false
        }
      }
    }

    groups.foreach {
      group =>
        nodes.foreach {
          node =>
            if (!group.getSubNodes.contains(node)) {
              group.addSubNodes(node)
            }
        }
    }
    true
  }
}
