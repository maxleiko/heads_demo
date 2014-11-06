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
package org.kevoree.tools.ui.editor.command


import org.kevoree.tools.ui.framework.ErrorHighlightableElement
import org.slf4j.LoggerFactory
import org.kevoree.tools.ui.editor.{ErrorPanel, KevoreeUIKernel}


class CheckCurrentModel extends Command {

  var logger = LoggerFactory.getLogger(this.getClass)

  var kernel: KevoreeUIKernel = null

  def setKernel(k: KevoreeUIKernel) = kernel = k

  var objectInError: List[ErrorHighlightableElement] = List()

  val scheduledExecutorService : java.util.concurrent.ScheduledExecutorService = java.util.concurrent.Executors.newSingleThreadScheduledExecutor()

  object EffectiveCheck extends Runnable {
    def run(){
      effectiveCheck();
    }
  }

  def execute(p: Object) {
    scheduledExecutorService.schedule(EffectiveCheck,1000,java.util.concurrent.TimeUnit.MILLISECONDS)
  }

  def effectiveCheck() {

    /*
    val previousNoError = objectInError.isEmpty
    objectInError.foreach(o => o.setState(ErrorHighlightableElement.STATE.NO_ERROR))
    objectInError = List()
    ErrorPanel.clear()
    val result = checker.check(kernel.getModelHandler.getActualModel)
    import scala.collection.JavaConversions._
    result.foreach({
      res =>
        ErrorPanel.displayError(res)
        logger.warn("Violation msg=" + res.getMessage)
        //AGFFICHE OBJET ERROR
        res.getTargetObjects.foreach {
          target =>
            //println(target)
            val uiObj = kernel.getUifactory.getMapping.get(target)
            if (uiObj != null) {
              //println("ui=" + uiObj)
              uiObj match {
                case hobj: ErrorHighlightableElement => {
                  objectInError = objectInError ++ List(hobj)
                  hobj.setState(ErrorHighlightableElement.STATE.IN_ERROR)
                }
                case _@e => logger.error("checker obj = " + e)
              }
            }
        }
    })
    if (previousNoError && !result.isEmpty){
      kernel.getModelPanel.repaint()
      kernel.getModelPanel.revalidate()
    }
       */

  }


}