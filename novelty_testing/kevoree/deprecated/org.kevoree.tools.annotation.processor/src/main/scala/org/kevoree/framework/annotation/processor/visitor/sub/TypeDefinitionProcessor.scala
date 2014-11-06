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
package org.kevoree.framework.annotation.processor.visitor.sub

import org.kevoree.framework.annotation.processor.LocalUtility

import org.kevoree._

/**
 * Created by IntelliJ IDEA.
 * User: duke
 * Date: 26/09/11
 * Time: 15:29
 */

trait TypeDefinitionProcessor {

  def defineAsSuperType[A<:TypeDefinition](child: TypeDefinition, parentName: String, parentType : Class[A], isAbstract : Boolean = false) {
    val model = LocalUtility.root
    val parent = model.findByPath("typeDefinitions[" + parentName + "]",parentType) match {
      case foundTD : Any => foundTD
      case null => {
        val newTypeDef = parentType.getSimpleName match {
          case "NodeType" => LocalUtility.kevoreeFactory.createNodeType
          case "ComponentType" => LocalUtility.kevoreeFactory.createComponentType
          case "ChannelType" => LocalUtility.kevoreeFactory.createChannelType
          case "GroupType" => LocalUtility.kevoreeFactory.createGroupType
          case _ @ notFound => println("Error: Unable to find type annotation for " + parentType.getSimpleName + " in " + parentName) ;null
        }
        newTypeDef.setName(parentName)
        model.addTypeDefinitions(newTypeDef)

        if (isAbstract) {
          newTypeDef.setAbstract(true)
        }
        newTypeDef
      }
    }
    child.addSuperTypes(parent)
  }

}