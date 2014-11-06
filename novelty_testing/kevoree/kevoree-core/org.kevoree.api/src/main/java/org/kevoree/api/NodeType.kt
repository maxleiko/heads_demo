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
package org.kevoree.api

import org.kevoree.ContainerRoot
import org.kevoree.api.adaptation.AdaptationModel
import org.kevoree.api.adaptation.AdaptationPrimitive

/**
 * Created by IntelliJ IDEA.
 * User: duke
 * Date: 31/12/11
 * Time: 09:50
 */

trait NodeType {

    //fun startNode(): Unit

    //fun stopNode(): Unit

    fun plan(actualModel: ContainerRoot, targetModel: ContainerRoot): AdaptationModel

    fun getPrimitive(primitive: AdaptationPrimitive): PrimitiveCommand

}