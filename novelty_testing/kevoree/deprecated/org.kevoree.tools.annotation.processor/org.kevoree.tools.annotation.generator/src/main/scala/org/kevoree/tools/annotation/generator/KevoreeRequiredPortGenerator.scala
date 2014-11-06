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
package org.kevoree.tools.annotation.generator

import javax.annotation.processing.Filer
import javax.tools.StandardLocation
import org.kevoree.tools.annotation.generator
import org.kevoree.{ComponentType => KevoreeComponentType, _}
import org.kevoree.annotation.ThreadStrategy
import scala.collection.JavaConversions._
import java.io.Writer
import scala.Tuple2


object KevoreeRequiredPortGenerator {

  def generate(root: ContainerRoot, filer: Filer, ct: KevoreeComponentType, ref: PortTypeRef) {
    // var portPackage = ct.getFactoryBean().substring(0, ct.getFactoryBean().lastIndexOf("."));

    val portPackage = GeneratorHelper.getTypeDefinitionGeneratedPackage(ct)
    val portName = ct.getName + "PORT" + ref.getName
    val wrapper = filer.createResource(StandardLocation.SOURCE_OUTPUT, "", new String(portPackage.replace(".", "/") + "/" + portName + ".kt"))
    val writer = wrapper.openWriter()

    writer.append("package " + portPackage + "\n")
    writer.append("import org.kevoree.framework.port.*\n")
    writer.append("import " + GeneratorHelper.getTypeDefinitionBasePackage(ct) + ".*\n")
    var baseName = ref.getRef.getName
    if (ref.getRef.isInstanceOf[MessagePortType]) {
      baseName = "org.kevoree.framework.MessagePort"
    }

    writer.append("class " + portName + "(val component : " + ct.getName + ") : " + baseName + " , ")
    ThreadingMapping.getMappings.get(Tuple2(ct.getName, ref.getName)) match {
      case ThreadStrategy.THREAD_QUEUE => {
        writer.append("KevoreeRequiredThreadPort {\n")
        writer.append("override var delegate: org.kevoree.framework.KevoreeChannelFragment? = null\n")
        writer.append("override val queue : java.util.concurrent.LinkedBlockingDeque<Any?> = java.util.concurrent.LinkedBlockingDeque<Any?>()\n")
        writer.append("override var reader : java.lang.Thread? = null\n")
      }
      case ThreadStrategy.SHARED_THREAD => {
        writer.append("KevoreeRequiredExecutorPort {\n")
        writer.append("override var pool: PausablePortThreadPoolExecutor? = null\n")
        writer.append("override var delegate: org.kevoree.framework.KevoreeChannelFragment? = null\n")
      }
      case ThreadStrategy.NONE => {
        writer.append("KevoreeRequiredNonePort {\n")
        writer.append("override var delegate: org.kevoree.framework.KevoreeChannelFragment? = null\n")
      }
      case _ => {
        writer.append("KevoreeRequiredExecutorPort {\n")
        writer.append("override var pool: PausablePortThreadPoolExecutor? = null\n")
        writer.append("override var delegate: org.kevoree.framework.KevoreeChannelFragment? = null\n")
      }
    }

    writer.append("override var isPaused : jet.Boolean = true\n")
    writer.append("override var _isBound : jet.Boolean = false\n")
    writer.append("override fun getName() : String { return \"" + ref.getName + "\"}\n")
    writer.append("override fun getComponentName() : String? { return component.getName() }\n")

    ref.getRef match {
      case mPT: MessagePortType => {
        /* GENERATE METHOD MAPPING */
        writer.append("override fun process(p0 : Any?) {this.send(p0)}\n")
        writer.append("override fun getInOut():Boolean {return false}\n")
      }

      case sPT: ServicePortType => {
        writer.append("override fun getInOut():Boolean {return true}\n")

        /* CREATE INTERFACE ACTOR MOK */
        /*sPT.getOperations.foreach {
          op =>
          /* GENERATE METHOD SIGNATURE */
            writer.append("override fun " + op.getName + "(")
            var i = 0
            op.getParameters.sortWith((p1, p2) => p2.getOrder > p1.getOrder).foreach {
              param =>
                if (i != 0) {
                  writer.append(",")
                }
                writer.append(GeneratorHelper.protectReservedWord(param.getName) + ":" + GeneratorHelper.protectedType(Printer.print(param.getType, '<', '>')) + "?")
                i = i + 1
            }

            var rt = op.getReturnType.getName
            if (op.getReturnType.getGenericTypes.size > 0) {
              rt += op.getReturnType.getGenericTypes.collect {
                case s: TypedElement => s.getName
              }.mkString("<", ",", ">")
            }

            if (isPrimitiveJava(rt)) {
              writer.append(") : " + GeneratorHelper.protectedType(rt) + " {\n")
            } else {
              writer.append(") : " + GeneratorHelper.protectedType(rt) + "? {\n")
            }

            /* Generate method corpus */
            /* CREATE MSG OP CALL */
            writer.append("val msgcall = org.kevoree.framework.MethodCallMessage()\n")
            writer.append("msgcall.setMethodName(\"" + op.getName + "\")\n")
            op.getParameters.sortWith((p1, p2) => p2.getOrder > p1.getOrder).foreach {
              param =>
                writer.append("msgcall.getParams()!!.put(\"" + param.getName + "\"," + GeneratorHelper.protectReservedWord(param.getName) + " as Any)\n")
            }

            if (isPrimitiveJava(rt)) {
              writer.append("return this.sendWait(msgcall) as " + GeneratorHelper.protectedType(rt))
            } else {
              writer.append("return this.sendWait(msgcall) as? " + GeneratorHelper.protectedType(rt))
            }

            writer.append("}\n")
        }*/
        // generate for superType of the current portType
        generateOperationForType(sPT, writer)
      }

    }

    writer.append("}\n")
    writer.close()
  }

  private def generateOperationForType(sPT : ServicePortType, writer : Writer) {
    sPT.getOperations.foreach {
      op =>
      /* GENERATE METHOD SIGNATURE */
        writer.append("override fun " + op.getName + "(")
        var i = 0
        op.getParameters.sortWith((p1, p2) => p2.getOrder > p1.getOrder).foreach {
          param =>
            if (i != 0) {
              writer.append(",")
            }
            writer.append(GeneratorHelper.protectReservedWord(param.getName) + ":" + GeneratorHelper.protectedType(Printer.print(param.getType, '<', '>')) + "?")
            i = i + 1
        }

        var rt = op.getReturnType.getName
        if (op.getReturnType.getGenericTypes.size > 0) {
          rt += op.getReturnType.getGenericTypes.collect {
            case s: TypedElement => s.getName
          }.mkString("<", ",", ">")
        }

        if (isPrimitiveJava(rt)) {
          writer.append(") : " + GeneratorHelper.protectedType(rt) + " {\n")
        } else {
          writer.append(") : " + GeneratorHelper.protectedType(rt) + "? {\n")
        }

        /* Generate method corpus */
        /* CREATE MSG OP CALL */
        writer.append("val msgcall = org.kevoree.framework.MethodCallMessage()\n")
        writer.append("msgcall.setMethodName(\"" + op.getName + "\")\n")
        op.getParameters.sortWith((p1, p2) => p2.getOrder > p1.getOrder).foreach {
          param =>
            writer.append("msgcall.getParams()!!.put(\"" + param.getName + "\"," + GeneratorHelper.protectReservedWord(param.getName) + " as Any)\n")
        }

        if (isPrimitiveJava(rt)) {
          writer.append("return this.sendWait(msgcall) as " + GeneratorHelper.protectedType(rt))
        } else {
          writer.append("return this.sendWait(msgcall) as? " + GeneratorHelper.protectedType(rt))
        }

        writer.append("}\n")
    }
    sPT.getSuperTypes.foreach(
      supertype => if (supertype.isInstanceOf[PortType]) {
        generateOperationForType(supertype.asInstanceOf[ServicePortType], writer)
      })
  }


  def isPrimitiveJava(name: String): Boolean = {
    if (name == "Boolean") {
      return true;
    }
    if (name == "Int") {
      return true;
    }
    if (name == "Long") {
      return true;
    }
    if (name == "void") {
      return true;
    }
    return false;
  }


}
