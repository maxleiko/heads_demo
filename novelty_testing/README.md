### HEADS Demo - Novelty Testing
Content:
 - kevoree/
 - kevoree-library/
 - kevoree-java-novelty-tester/
 - push-model/
 - models/
 - kevoree-5.1.3.jar
 - README.md

#### First steps - compilation
Compile Kevoree 5.1.4-SNAPSHOT
```sh
cd kevoree
mvn install
cd ..
```

Compile RemoteWSChan & JavaNode using Kevoree 5.1.4-SNAPSHOT
```
cd kevoree-library/java/org.kevoree.library.java.javaNode
mvn install
cd ../org.kevoree.library.java.ws
mvn install
cd ../../..
```

Compile Novelty search component
```
cd kevoree-java-novelty-tester
mvn clean install
cd ..
```


#### Start the 3 Kevoree runtimes
`jsNode` **js** runtime
```sh
kevoreejs -n jsNode -k models/base.kevs
```

`javaNode` **java** runtime  
```sh
java -Dnode.name=javaNode -Dnode.bootstrap=models/base.kevs -Dversion=5.1.4-SNAPSHOT -jar kevoree-5.1.3.jar
```

`node0` **java** runtime  
```sh
java -Dnode.bootstrap=models/base.kevs -Dversion=5.1.4-SNAPSHOT -jar kevoree-5.1.3.jar
```

#### Deploy a first model with the tester stopped
```sh
cd push-model
node push stopped.json
```

#### Deploy a second model to start the tester
```sh
cd push-model
node push started.json
```

#### Write the logs
Wait until the process triggered by `node push started.json` is done (16 answers should show up in node0's console)  
Then use the `ToyConsole` input and send the command `write`, in order to trigger the logging process.
