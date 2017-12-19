# Java class loader

Console application for dynamic reloading of existing development functionality. 
A piece of functionality is placed in resource directory and archived in jar. 
The application has console menu for choosing option, the output is done through usage of log4j library.

## Getting Started

### Create fat jar file
Go to src/main/resources and create
```
out/
libs/
```
Compile the code:
```
javac -cp ./src/main/java ./src/main/java/com/test/module/model/*.java -d ./out/
```
Compile the code with dependencies:
```
javac -cp ./src/main/java ./src/main/java/com/test/module/model/*.java -d ./out/ -classpath ./libs/log4j-api-2.10.0.jar
```
Create jar file:
```
jar cvfm Module.jar ./src/main/resources/META-INF/MANIFEST.FM -C out/ .
```

