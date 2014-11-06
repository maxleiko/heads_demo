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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.kevoree.tools.marShell.parser.sub

import org.kevoree.tools.marShell.ast._
import org.kevoree.tools.marShell.ast.AddPortTypeStatment
import org.kevoree.tools.marShell.ast.CreateComponentTypeStatment
import org.kevoree.tools.marShell.ast.CreateChannelTypeStatment
import collection.immutable.HashMap


trait KevsTypeParser extends KevsAbstractParser {

  def parseType: Parser[List[Statment]] = parseCreateComponentType | parseCreateChannelType | parseAddInPortType | parseAddOutPortType | parseCreateDictionaryType


  //example : removeNode node1,node2
  val createComponentTypeCommandFormat = "createComponentType <ComponentTypeName> @ <libraryName> "

  def parseCreateComponentType: Parser[List[Statment]] = "createComponentType" ~ orFailure((ident ~ opt("@" ~> ident)), createComponentTypeCommandFormat) ^^ {
    case _ ~ nodeIDs =>
      nodeIDs match {
        case id ~ lib => List(CreateComponentTypeStatment(id, lib))
      }
  }

  val createChannelTypeCommandFormat = "createChannelType <ChannelTypeName> @ <libraryName>"

  def parseCreateChannelType: Parser[List[Statment]] = "createChannelType" ~ orFailure((ident ~ opt("@" ~> ident)), createChannelTypeCommandFormat) ^^ {
    case _ ~ nodeIDs =>
      nodeIDs match {
        case id ~ lib => List(CreateChannelTypeStatment(id, lib))
      }
  }

  def parsePortType: Parser[Option[String]] = opt(":" ~> ident)

  // val createChannelTypeCommandFormat = "createChannelType <ChannelTypeName> @ <libraryName>"
  val addInPortTypeCommandFormat = "addInPortType <PortName> : <Message | Service : interfaceName > : <optional> => <ComponentTypeName>"

  def parseAddInPortType: Parser[List[Statment]] =
    "addInPortType" ~
      orFailure(ident, addOutPortTypeCommandFormat) ~
      orFailure(":", addOutPortTypeCommandFormat) ~
      orFailure("Message" | "Service", addOutPortTypeCommandFormat) ~
      orFailure(opt(":" ~> stringLit), addOutPortTypeCommandFormat) ~
      orFailure(opt(":" ~> "optional"), addOutPortTypeCommandFormat) ~
      orFailure("=>", addOutPortTypeCommandFormat) ~
      orFailure(ident, addOutPortTypeCommandFormat) ^^ {
      case _ ~ portTypeName ~ _ ~ typeport ~ oPTClassName ~ optional ~ _ ~ targetTypeName => List(AddPortTypeStatment(portTypeName, targetTypeName, typeport, oPTClassName, optional, true))
    }


  val addOutPortTypeCommandFormat = "addOutPortType <PortName> : <Message | Service : interfaceName > : <optional> => <ComponentTypeName> "

  def parseAddOutPortType: Parser[List[Statment]] =
    "addOutPortType" ~
      orFailure(ident, addOutPortTypeCommandFormat) ~
      orFailure(":", addOutPortTypeCommandFormat) ~
      orFailure("Message" | "Service", addOutPortTypeCommandFormat) ~
      orFailure(opt(":" ~> stringLit), addOutPortTypeCommandFormat) ~
      orFailure(opt(":" ~> "optional"), addOutPortTypeCommandFormat) ~
      orFailure("=>", addOutPortTypeCommandFormat) ~
      orFailure(ident, addOutPortTypeCommandFormat) ^^ {
      case _ ~ portTypeName ~ _ ~ typeport ~ oPTClassName ~ optional ~ _ ~ targetTypeName => List(AddPortTypeStatment(portTypeName, targetTypeName, typeport, oPTClassName, optional, false))
    }


  val createDictionaryTypeCommandFormat = "createDictionaryType myNewComponent {\n        optional myParam : \"String\" default \"\",\n        myParam2 : \"String\"\n    } "

  def parseCreateDictionaryType: Parser[List[Statment]] =
    "createDictionaryType" ~
      orFailure(ident, createDictionaryTypeCommandFormat) ~
      orFailure("{", createDictionaryTypeCommandFormat) ~
      orFailure(repsep(parseCreateDictionaryTypeElem, ","), createDictionaryTypeCommandFormat) ~
      orFailure("}", createDictionaryTypeCommandFormat) ^^ {
      case _ ~ typeID ~ _ ~ elems ~ _ => List(CreateDictionaryTypeStatment(typeID, elems))
    }


  def parseCreateDictionaryTypeElem: Parser[DictionarySubStatement] = {
    opt("optional") ~opt("fragDep") ~ ident ~ ":" ~ stringLit ~ opt("values" ~> stringLit) ~ opt("default" ~> stringLit) ^^ {
      case optKeyWord ~ optFragDep ~ elemID ~ _ ~ dataType ~ optVals ~ optDef => {
        DictionarySubStatement(elemID, optKeyWord.isDefined, dataType, optVals, optDef,optFragDep.isDefined)
      }
    }
  }


}
