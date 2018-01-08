# Multithreading: Classic Model and Concurrency

1. Create a test java program, which creates a large number of objects with different size. Program keeps for some time objects. 
Objects must have references to each other (when creating the next bundle of objects, you need to create a connection between them in a random way). 
There must be so many objects that the program was hard to work because of the constant filling of the heap and cleaning it GC

2. Create different GC configurations (CMS, G1, Parallel, Serial, etc.) with different parameters

3. Сonfigure GC log recordinbg. Identify how much time does it take to stop JVM and how much how much data has been cleared.
Create log visualisation with gcviewer program
Try to use JVisualVM and VisualGC plugin

4. Achieve OutOfMemoryException and StackOverflowError. Explain how it happened.

5. Compare GC. Which one works better and when.

6. Create instruction with start parameters for each GC, log GC, screenshots from GCViewer and VisualGC 

## Getting Started

### Prerequisites

* [Gradle](https://gradle.org/) - Dependency Management, [how to install](https://gradle.org/install/)

### Run first task application
```
./gradlew gc:runApp -Dgc.objectsNumber="5000"
```
gc.objectsNumber - the number of objects to create

### Run application with StackOverflowError
```
./gradlew gc:stackOverflowRunApp -Dgc.jvm="-Xss1m"
```

### Run application with OutOfMemoryException
```
./gradlew gc:runApp -Dgc.objectsNumber="15000" -Dgc.jvm="-Xmx1g -Xms500m -Xmn250m"
```

## Tasks
4. StackOverflowError and OutOfMemoryException
StackOverflowError happened because function recursively call itself.
Each function call puts new frame on top of stack. After a lot of calls, stack memory is end.
OutOfMemoryException happened because there is a lot of objects in heap.

5. Compare GC. Which one works better and when.
[GC report](docs/GC.md)

## Built With

* [Gradle](https://gradle.org/) - Dependency Management

## Authors

* **Siarhei Blashuk** - *Developer* - [BSGfB](https://github.com/BSGfB)
