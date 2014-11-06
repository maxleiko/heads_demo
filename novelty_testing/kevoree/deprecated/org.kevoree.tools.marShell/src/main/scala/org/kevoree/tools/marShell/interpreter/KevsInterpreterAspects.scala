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

package org.kevoree.tools.marShell.interpreter

import sub._
import org.kevoree.tools.marShell.ast._
import org.kevoree.log.Log

object KevsInterpreterAspects {

  implicit def rich(o: Script) = KevsScriptInterpreter(o)

  implicit def rich(o: TransactionalBloc) = KevsAddTBlockInterpreter(o)

  implicit def rich(o: AddNodeStatment) = KevsAddNodeInterpreter(o)


  implicit def rich(o: Object): KevsAbstractInterpreter = {
    o match {
      case b: Block => b match {
        case tb: TransactionalBloc => KevsAddTBlockInterpreter(tb)
      }
      case st: Statment => st match {
        case includeSt: IncludeStatement => KevsIncludeInterpreter(includeSt)

        case mergeSt: MergeStatement => KevsMergerInterpreter(mergeSt)

        case addst: AddComponentInstanceStatment => KevsAddComponentInstanceInterpreter(addst)
        case removest: RemoveComponentInstanceStatment => KevsRemoveComponentInstanceInterpreter(removest)

        case addChannel: AddChannelInstanceStatment => KevsAddChannelInterpreter(addChannel)
        case removeChannel: RemoveChannelInstanceStatment => KevsRemoveChannelInterpreter(removeChannel)
        case addNodest: AddNodeStatment => KevsAddNodeInterpreter(addNodest)
        case removeNodest: RemoveNodeStatment => KevsRemoveNodeInterpreter(removeNodest)
        case addBinding: AddBindingStatment => KevsAddBindingInterpreter(addBinding)
        case removeBinding: RemoveBindingStatment => KevsRemoveBindingInterpreter(removeBinding)

        case updateDictionary: UpdateDictionaryStatement => KevsUpdateDictionaryInterpreter(updateDictionary)

        case addGroup: AddGroupStatment => KevsAddGroupInterpreter(addGroup)
        case removeGroup: RemoveGroupStatment => KevsRemoveGroupInterpreter(removeGroup)

        case addToGroupStatement: AddToGroupStatement => KevsAddToGroupInterpreter(addToGroupStatement)
        case removeFromGroupStatement: RemoveFromGroupStatement => KevsRemoveFromGroupInterpreter(removeFromGroupStatement)

        case moveComponent: MoveComponentInstanceStatment => KevsMoveComponentInstanceInterpreter(moveComponent)

        //Library aspect
        case addLibrary: AddLibraryStatment => KevsAddLibraryInterpreter(addLibrary)
        case removeLibrary: RemoveLibraryStatment => KevsRemoveLibraryInterpreter(removeLibrary)
        case addDeployUnit: AddDeployUnitStatment => KevsAddDeployUnitInterpreter(addDeployUnit)


        //TYPE ASPECT
        case createComponentType: CreateComponentTypeStatment => KevsCreateComponentTypeInterpreter(createComponentType)
        case createChannelType: CreateChannelTypeStatment => KevsCreateChannelTypeInterpreter(createChannelType)
        case addPortType: AddPortTypeStatment => KevsAddPortTypeInterpreter(addPortType)
        case stmt: CreateDictionaryTypeStatment => KevsCreateDictionaryTypeInterpreter(stmt)


          //ADD REPO INTERPRETER
        case addRepo: AddRepoStatment => KevsAddRepoInterpreter(addRepo)


        //Network
        case networkStatement: NetworkPropertyStatement => KevsNetworkInterpreter(networkStatement)

        // NODE ASPECT
        case addChildStatment: AddChildStatment => KevsAddChildInterpreter(addChildStatment)
        case removeChildStatment: RemoveChildStatment => KevsRemoveChildInterpreter(removeChildStatment)
        case moveChildStatment: MoveChildStatment => KevsMoveChildInterpreter(moveChildStatment)

          // START and STOP
        case startNStopInstanceStatement : StartNStopInstanceStatment => KevsStartNStopInstanceInterpreter(startNStopInstanceStatement)

      }
      case _@e => Log.error("", e.asInstanceOf[Throwable]); null
    }
  }
}
