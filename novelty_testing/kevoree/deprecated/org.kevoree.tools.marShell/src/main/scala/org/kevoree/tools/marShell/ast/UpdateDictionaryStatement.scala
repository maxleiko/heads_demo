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

package org.kevoree.tools.marShell.ast

case class UpdateDictionaryStatement(instanceName : String,nodeName:Option[String],fraProperties : java.util.HashMap[String,java.util.Properties]) extends Statment {
  def getTextualForm: String = {
    "updateDictionary "+instanceName+getNodeName+" "+generateDico(fraProperties)
  }

  def generateDico(fraProperties : java.util.HashMap[String,java.util.Properties]): String = {
    import scala.collection.JavaConversions._
    var res = List[String]()
    fraProperties.foreach{ t =>
      if(!t._2.isEmpty && t._1 != "*"){
        res = res ++ List(AstHelper.toStringDictionary(t._2)+"@"+t._1)
      }
    }
    res.mkString(",")
  }

  def getNodeName : String = {
    nodeName match {
      case Some(n) if (n!="*")=> "@"+n
      case _ => ""
    }
  }

}
