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
package org.kevoree.tools.arduino.framework.impl;

import org.kevoree.ContainerRoot;
import org.kevoree.TypeDefinition;
import org.kevoree.tools.arduino.framework.ArduinoGenerator;

import java.io.InputStream;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: duke
 * Date: 18/01/12
 * Time: 16:36
 * To change this template use File | Settings | File Templates.
 */
public class DefaultArduinoGenerator implements ArduinoGenerator {

    private StringBuffer buffer = new StringBuffer();

    private HashMap<String,InputStream> streamPaths = new HashMap<String,InputStream>();
    
    @Override
    public void addLibrary(String name, InputStream stream) {
        streamPaths.put(name,stream);
    }

    @Override
    public void razLibrary() {
        streamPaths.clear();
    }

    @Override
    public Set<String> getLibraryKeys() {
        return streamPaths.keySet();
    }

    @Override
    public InputStream getLibrary(String key) {
        return streamPaths.get(key);
    }

    @Override
    public void declareStaticKMessage(String name, String typeName) {
        buffer.append("kmessage * "+name+" = (kmessage*) malloc(sizeof(kmessage));\n");
        buffer.append("if ("+name+"){memset("+name+", 0, sizeof(kmessage));}\n");
        buffer.append(name+"->metric = \""+typeName+"\";");
    }

    @Override
    public void freeStaticKMessage(String name) {
        buffer.append("free("+name+");\n");
    }

    @Override
    public void sendKMessage(String name, String portName) {
        buffer.append(portName+"_rport("+name+");\n");
    }

    @Override
    public void razGen() {
        buffer = new StringBuffer();
    }

    @Override
    public void appendNativeStatement(String statement) {
        buffer.append(statement);
        buffer.append("\n");
    }

    public String getContent(){
        return buffer.toString();
    }

    private TypeDefinition typeDefModel = null;
    
    @Override
    public TypeDefinition getTypeModel() {
        return typeDefModel; 
    }

    @Override
    public void setTypeModel(TypeDefinition td) {
        typeDefModel = td;
    }

    private String portName = "";
    
    @Override
    public void setPortName(String pname) {
        portName = pname;
    }

    @Override
    public String getPortName() {
        return portName;
    }

}
