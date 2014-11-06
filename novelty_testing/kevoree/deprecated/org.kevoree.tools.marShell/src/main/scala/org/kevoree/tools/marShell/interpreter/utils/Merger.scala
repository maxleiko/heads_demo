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

package org.kevoree.tools.marShell.interpreter.utils

import org.kevoree.{ContainerRoot, ContainerNode, Instance, KevoreeFactory}
import org.kevoree.impl.DefaultKevoreeFactory


object Merger {

  private val kevoreeFactory: KevoreeFactory = new DefaultKevoreeFactory

  def mergeFragmentDictionary(inst: Instance, fragmentProps: java.util.HashMap[String, java.util.Properties]) = {
    import scala.collection.JavaConversions._

    fragmentProps.keySet().foreach {
      propKey =>
        propKey match {
          case "*" => mergeDictionary(inst, fragmentProps.get(propKey), null)
          case _@searchNodeName => {
            inst.getTypeDefinition.eContainer.asInstanceOf[ContainerRoot].getNodes.find(n => n.getName == searchNodeName) match {
              case Some(nodeFound) => {
                mergeDictionary(inst, fragmentProps.get(propKey), nodeFound)
              }
              case None => {
                throw new Exception("Unknown nodeName for name " + searchNodeName)
              }
            }
          }
        }
    }
  }


  /* Goal of this method is to merge dictionary definition with already exist instance defintion */
  def mergeDictionary(inst: Instance, props: java.util.Properties, targetNode: ContainerNode) = {
    import scala.collection.JavaConversions._
    props.keySet.foreach {
      key =>
        val newValue = props.get(key)

        var dictionary = inst.getDictionary
        if (dictionary == null) {
          dictionary = kevoreeFactory.createDictionary
          inst.setDictionary(dictionary)
        }

        inst.getDictionary.getValues.find(value => {
          (value.getAttribute.getName == key) && (if (targetNode != null) {
            value.getTargetNode != null && value.getTargetNode.getName == targetNode.getName
          } else {
            value.getTargetNode == null
          })
        }) match {
          //UPDATE VALUE CASE
          case Some(previousValue) => {
            previousValue.setValue(newValue.toString)
          }
          //MERGE NEW Dictionary Attribute
          case None => {
            // CHECK if dictionary type exist
            if (inst.getTypeDefinition.getDictionaryType != null) {
              //CHECK IF ATTRIBUTE ALREADY EXIST WITHOUT VALUE
              val att = inst.getTypeDefinition.getDictionaryType.getAttributes.find(att => att.getName == key) match {
                case None => {
                  /* if(allowTypeUpdate){
                     val newDictionaryValue = kevoreeFactory.createDictionaryAttribute
                     newDictionaryValue.setName(key.toString)
                     inst.getTypeDefinition.getDictionaryType.get.addAttributes(newDictionaryValue)
                     newDictionaryValue
                   } else { */
                  throw new Exception("Dictionary Type does not contain attribute named -" + key + "- type modification not allowed in this scope")
                  // }
                }
                case Some(previousAtt) => previousAtt
              }
              val newDictionaryValue = kevoreeFactory.createDictionaryValue
              newDictionaryValue.setValue(newValue.toString)
              newDictionaryValue.setAttribute(att)
              newDictionaryValue.setTargetNode(targetNode)
              inst.getDictionary.addValues(newDictionaryValue)
            }
          }
        }

    }

  }

}
