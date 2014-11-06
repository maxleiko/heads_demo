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
package org.kevoree.tools.model2code.genericSub

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
import japa.parser.ast.expr.NameExpr
import japa.parser.ast.{CompilationUnit, ImportDeclaration}
import scala.collection.JavaConversions._

/**
 * Created by IntelliJ IDEA.
 * User: Gregory NAIN
 * Date: 26/07/11
 * Time: 10:29
 */

trait ImportSynchMethods {

  def compilationUnit : CompilationUnit

   def checkOrAddImport(classQName : String) {
    compilationUnit.getImports.find({importDecl => importDecl.getName.toString.equals(classQName)}) match {
      case None => {
          compilationUnit.getImports.add(new ImportDeclaration(new NameExpr(classQName), false, false))
        }
      case Some(s)=>
    }
  }

  def checkOrRemoveImport(classQName : String) {
    compilationUnit.getImports.find({importDecl => importDecl.getName.toString.equals(classQName)}) match {
      case None => //done
      case Some(s)=> {
          //TODO: check if class is still used in CU, remove if not
        }
    }
  }


}