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

package org.kevoree.framework.annotation.processor.visitor

import org.kevoree.TypedElement
import org.kevoree.KevoreeFactory
import org.kevoree.framework.annotation.processor.LocalUtility
import javax.lang.model.util.SimpleTypeVisitor6
import javax.lang.model.`type`._
import javax.lang.model.element.Element
import java.util.StringTokenizer


class DataTypeVisitor extends SimpleTypeVisitor6[Any, Any] {

  var dataType = LocalUtility.kevoreeFactory.createTypedElement

  def getDataType(): TypedElement = {
    dataType
  }


  override def visitDeclared(p1: _root_.javax.lang.model.`type`.DeclaredType, p: Any) {
    dataType.setName(p1.asElement().toString)
    import scala.collection.JavaConversions._
    p1.getTypeArguments.foreach {
      tm =>
        val dtv = new DataTypeVisitor()
        tm.accept(dtv, tm)
        dataType.addGenericTypes(LocalUtility.getOraddDataType(dtv.getDataType))
    }
  }

  override def visitArray(p1: _root_.javax.lang.model.`type`.ArrayType, p: Any) {
    var name = p1.toString
    name = name.replace("[]", "")
    val sb = new StringBuffer()
    sb.append("Array[")
//    sb.append(name.substring(0, 1).toUpperCase)
//    sb.append(name.substring(1).toLowerCase)
    sb.append(visitArrayComponentType(p1.getComponentType))
    sb.append("]")
    dataType.setName(sb.toString)
  }

  override def visitPrimitive(p1: _root_.javax.lang.model.`type`.PrimitiveType, p: Any) {
    p1.getKind match {
      case TypeKind.BOOLEAN => dataType.setName("Boolean")
      case TypeKind.BYTE => dataType.setName("Byte")
      case TypeKind.CHAR => dataType.setName("Char")
      case TypeKind.DOUBLE => dataType.setName("Double")
      case TypeKind.FLOAT => dataType.setName("Float")
      case TypeKind.INT => dataType.setName("Int")
      case TypeKind.LONG => dataType.setName("Long")
      case TypeKind.SHORT => dataType.setName("Short")
      case TypeKind.ARRAY => {
        dataType.setName(p1.toString)
      }
    }
  }

  override def visitNoType(p1: _root_.javax.lang.model.`type`.NoType, p: Any) {
    dataType.setName("void")
  }

  def visitEnumType(t: DeclaredType) {
    dataType.setName(t.asElement().toString)
  }

  private def visitArrayComponentType(p1 : javax.lang.model.`type`.TypeMirror) : String = {
    val dtv = new DataTypeVisitor()
    p1.accept(dtv, p1)
    dtv.getDataType.getName
  }

  /*
def visitInterfaceType(t:InterfaceType)= {
import scala.collection.JavaConversions._
dataType.setName(t.getDeclaration.getQualifiedName);
t.getActualTypeArguments.foreach{tm=>
val dtv = new DataTypeVisitor();
tm.accept(dtv);
dataType.getGenericTypes.add(LocalUtility.getOraddDataType(dtv.getDataType()));
}
}      */

}
