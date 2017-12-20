# Java class loader

Console application for dynamic reloading of existing development functionality. 
A piece of functionality is placed in resource directory and archived in jar. 
The application has console menu for choosing option, the output is done through usage of log4j library.

## Getting Started

### Create jar file
Go to src/main/resources and create
```
out/
libs/
```

Create RussianLanguageModule module:
```
javac -cp ./src/main/java ./src/main/java/com/test/module/model/RussianLanguageModule.java -d ./out/ -classpath ./libs/log4j-core-2.10.0.jar:./libs/log4j-api-2.10.0.jar:./libs/LanguageModuleApi.jar
jar cvfm RussianLanguageModule.jar ./src/main/resources/META-INF/MANIFEST.MF -C out/ .
```
Create EnglishLanguageModule module:
```
javac -cp ./src/main/java ./src/main/java/com/test/module/model/EnglishLanguageModule.java -d ./out/ -classpath ./libs/log4j-core-2.10.0.jar:./libs/log4j-api-2.10.0.jar:./libs/LanguageModuleApi.jar
jar cvfm EnglishLanguageModule.jar ./src/main/resources/META-INF/MANIFEST.MF -C out/ .
```

### Run application
```
./gradlew classloader:clean classloader:build classloader:fatJar
java -jar "./classloader/build/libs/classloader-1.0-SNAPSHOT.jar"
```

## Built With

* [Gradle](https://gradle.org/) - Dependency Management

## Authors

* **Siarhei Blashuk** - *Developer* - [BSGfB](https://github.com/BSGfB)
