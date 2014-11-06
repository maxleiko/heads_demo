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

import org.kevoree.TypeDefinition
import javax.lang.model.element.TypeElement
import scala.collection.JavaConversions._
import org.kevoree.framework.annotation.processor.LocalUtility

trait DictionaryProcessor {

  def processDictionary(typeDef: TypeDefinition, classdef: TypeElement) = {
    /* CHECK DICTIONARY */
    if (classdef.getAnnotation(classOf[org.kevoree.annotation.DictionaryType]) != null) {
      classdef.getAnnotation(classOf[org.kevoree.annotation.DictionaryType]).value.foreach {
        dictionaryAtt =>

        //CASE NO DICTIONARY
          if (typeDef.getDictionaryType == null) {
            val newdictionary = LocalUtility.kevoreeFactory.createDictionaryType
            typeDef.setDictionaryType(newdictionary)
          }

          //CASE NO ATT ALREADY CREATED WITH NAME
          val processDictionaryAtt = typeDef.getDictionaryType.getAttributes.find(eAtt => eAtt.getName == dictionaryAtt.name) match {
            case None => {
              val newAtt = LocalUtility.kevoreeFactory.createDictionaryAttribute
              newAtt.setName(dictionaryAtt.name)
              typeDef.getDictionaryType.addAttributes(newAtt)
              newAtt.setFragmentDependant(dictionaryAtt.fragmentDependant())
              newAtt
            }
            case Some(att) => att
          }

          //INIT DEFAULT VALUE
          processDictionaryAtt.setOptional(dictionaryAtt.optional)

          //INIT DEF VALUE
          //TODO ALLOW MORE TYPE THAN STRING
          if (dictionaryAtt.defaultValue != "defaultKevoreeNonSetValue") {
            typeDef.getDictionaryType.getDefaultValues.find(defV => defV.getAttribute == processDictionaryAtt) match {
              case None => {
                val newVal = LocalUtility.kevoreeFactory.createDictionaryValue
                newVal.setAttribute(processDictionaryAtt)
                newVal.setValue(dictionaryAtt.defaultValue)
                typeDef.getDictionaryType.addDefaultValues(newVal)
              }
              case Some(edefV) => edefV.setValue(dictionaryAtt.defaultValue)
            }
          }
          if (!dictionaryAtt.vals().isEmpty) {
            processDictionaryAtt.setDatatype("enum=" + dictionaryAtt.vals().mkString(","))
          }

          var dataTypeName : String = ""
          try {
            dictionaryAtt.dataType()
          } catch {
            case e: javax.lang.model.`type`.MirroredTypeException =>
              dataTypeName = e.getTypeMirror.toString
          }

          if (dataTypeName != "java.lang.Void") {
            processDictionaryAtt.setDatatype("raw=" + dataTypeName)
          }
          processDictionaryAtt
      }
    }
  }

}
