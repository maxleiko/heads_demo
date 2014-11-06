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
package org.kevoree.tools.arduino.framework

/**
 * Created by IntelliJ IDEA.
 * User: duke
 * Date: 18/01/12
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */

object RawTypeHelper {

  def getArduinoType(baseType : String) : String = {
    baseType match {
      case "java.lang.Long" => "long"
      case "java.lang.Integer" => "int"
      case _ => baseType
    }
  }

  def isArduinoTypeArray(datatype : String) : Boolean ={
    datatype match {
    case  "org.kevoree.tools.arduino.framework.datatypes.IntList4" => true
    case _ => false
  }
  }

  /**
   * @param baseType  the type
   * @param attribute_name
   * @return   type  attribute_name [size]
   */
  def getArduinoTypeArray(baseType : String,attribute_name : String) : String = {
    baseType match {
      case "org.kevoree.tools.arduino.framework.datatypes.IntList4" =>  "int " + attribute_name + "[4];"
      case _ => baseType
    }
  }


}