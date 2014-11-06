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

package org.kevoree.tools.marShellGUI

import jsyntaxpane.DefaultSyntaxKit
import jsyntaxpane.util.Configuration

class KevsJSyntaxKit extends DefaultSyntaxKit(new KevsJSyntaxLexerWrapper()) {

  override def getContentType = "text/kevs; charset=UTF-8"

  var config = new java.util.Properties
  //config.setProperty("RightMarginColumn", "80")
  //config.setProperty("RightMarginColor", "0xdddddd")

  config.setProperty("Action.indent.WordRegex", "\\w+|\\/(\\*)+")
  config.setProperty("Action.combo-completion", "org.kevoree.tools.marShellGUI.KevsComboCompletionAction, control SPACE")
  config.setProperty("Action.combo-completion.MenuText", "Completions")
  config.setProperty("Action.double-quotes", "jsyntaxpane.actions.PairAction, typed \"")
 // config.setProperty("Action.double-quotes", "jsyntaxpane.actions.PairAction, typed \"")

  //config.setProperty("LineNumbers.CurrentBack","0x333300")



  config.setProperty("Style.IDENTIFIER","0xFFCE89, 1")
  config.setProperty("Style.DELIMITER","0xFFFFFF, 1")
  config.setProperty("Style.KEYWORD","0xFC6C1D, 1")
  config.setProperty("Style.KEYWORD2","0xFC6C1D, 3")

  config.setProperty("CaretColor","0xFFFFFF")
  config.setProperty("TokenMarker.Color","0x6D788F")
  config.setProperty("PairMarker.Color","0x6D788F")

  config.setProperty("LineNumbers.Background","0x646464")
  config.setProperty("LineNumbers.Foreground","0xFFFFFF")
  config.setProperty("LineNumbers.CurrentBack","0xF4A460")





     /*

     #
# These are the various Attributes for each TokenType.
# The keys of this map are the TokenType Strings, and the values are:
# color (hex, or integer), Font.Style attribute
# Style is one of: 0 = plain, 1=bold, 2=italic, 3=bold/italic
Style.OPERATOR = 0x000000, 0
Style.DELIMITER = 0x000000, 1
Style.KEYWORD = 0x3333ee, 0
Style.KEYWORD2 = 0x3333ee, 3
Style.TYPE = 0x000000, 2
Style.TYPE2 = 0x000000, 1
Style.TYPE3 = 0x000000, 3
Style.STRING = 0xcc6600, 0
Style.STRING2 = 0xcc6600, 1
Style.NUMBER = 0x999933, 1
Style.REGEX = 0xcc6600, 0
Style.IDENTIFIER = 0x000000, 0
Style.COMMENT = 0x339933, 2
Style.COMMENT2 = 0x339933, 3
Style.DEFAULT = 0x000000, 0
Style.WARNING = 0xCC0000, 0
Style.ERROR = 0xCC0000, 3

      */


  this.setConfig(config)



}
