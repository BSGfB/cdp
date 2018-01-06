# GC task report

This reports contains result of different gc running on the same task

GC log visualisation: [gceasy.io](http://gceasy.io/index.jsp)

## GC types

### Serial Collector
Options: -XX:+UseSerialGC
```
./gradlew gc:runApp -Dgc.objectsNumber="5000" -Dgc.jvm="-XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:/home/sergey/r/t/gc.log -XX:+UseSerialGC"
```

[Log](SerialGC.log)

[Report](SerialGC.pdf)

### Parallel Collector
Options: -XX:+UseParallelGC  -XX:ParallelGCThreads=2  -XX:ParallelGCThreads=4
```
./gradlew gc:runApp -Dgc.objectsNumber="5000" -Dgc.jvm="-XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:/home/sergey/r/m/cdp/gc/docs/ParallelGC_Thread_4.log -XX:+UseParallelGC -XX:ParallelGCThreads=4"
```

[Log with 2 threads](ParallelGC_Thread_2.log)

[Report with 2 threads](ParallelGC_Thread_2.pdf)

[Log with 4 threads](ParallelGC_Thread_4.log)

[Report with 4 threads](ParallelGC_Thread_4.pdf)

### Concurrent Mark Sweep
Options: -XX:+UseConcMarkSweepGC
```
./gradlew gc:runApp -Dgc.objectsNumber="5000" -Dgc.jvm="-XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:/home/sergey/r/m/cdp/gc/docs/ConcMarkSweepGC.log -XX:+UseConcMarkSweepGC"
```

[Log](ConcMarkSweepGC.log)

[Report](ConcMarkSweepGC.pdf)

### Garbage-First Garbage Collector
Options: -XX:+UseG1GC
```
./gradlew gc:runApp -Dgc.objectsNumber="5000" -Dgc.jvm="-XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:/home/sergey/r/m/cdp/gc/docs/G1GC.log -XX:+UseG1GC"
```

[Log](G1GC.log)

[Report](G1GC.pdf)