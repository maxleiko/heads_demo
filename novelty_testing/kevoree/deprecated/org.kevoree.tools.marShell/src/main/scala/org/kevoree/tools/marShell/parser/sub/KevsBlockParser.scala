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

package org.kevoree.tools.marShell.parser.sub

import org.kevoree.tools.marShell.ast.Block
import org.kevoree.tools.marShell.ast.Statment
import org.kevoree.tools.marShell.ast.TransactionalBloc

trait KevsBlockParser extends KevsAbstractParser {

  def parseTBlock : Parser[Block] = opt(parseBlockType) ~ "{" ~ parseStatmentList ~ "}" ^^  { case btype ~ _ ~ l ~ _ =>
     /* btype match {
        case "tblock" => TransactionalBloc(l)
        case _ => logger.error("TODO");null
      }  */
    TransactionalBloc(l)
  }

  def parseBlockType : Parser[String] = "tblock"
  
  def parseStatmentList : Parser[List[Statment]] = rep(kevStatement) ^^{ case l =>
      var res = new scala.collection.mutable.ArrayBuffer[Statment]()
      l.foreach{newl=>
        res.appendAll(newl)
      }
      res.toList
  }

  def parseBlockList : Parser[List[Statment]] = "{" ~ rep(kevStatement) ~ "}" ^^{ case _ ~ l ~ _ =>
    var res = new scala.collection.mutable.ArrayBuffer[Statment]()
    l.foreach{newl=>
      res.appendAll(newl)
    }
    res.toList
  }


  def parseImplicitTBlock : Parser[Block] = parseStatmentList ^^ {
    case l => TransactionalBloc(l)
  }


}
