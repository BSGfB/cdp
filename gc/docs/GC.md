# GC task report

This reports contains result of different gc running on the same task

GC log visualisation: [gceasy.io](http://gceasy.io/index.jsp)

## GC types

### Serial Collector
Options: -XX:+UseSerialGC
```
./gradlew gc:runApp -Dgc.objectsNumber="5000" -Dgc.jvm="-XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.log -XX:+UseSerialGC"
```

[Log](SerialGC.log)

[Report](SerialGC.pdf)

### Parallel Collector
Options: -XX:+UseParallelGC  -XX:ParallelGCThreads=2  -XX:ParallelGCThreads=4
```
./gradlew gc:runApp -Dgc.objectsNumber="5000" -Dgc.jvm="-XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:ParallelGC_Thread_4.log -XX:+UseParallelGC -XX:ParallelGCThreads=4"
```

[Log with 2 threads](ParallelGC_Thread_2.log)

[Report with 2 threads](ParallelGC_Thread_2.pdf)

[Log with 4 threads](ParallelGC_Thread_4.log)

[Report with 4 threads](ParallelGC_Thread_4.pdf)

### Concurrent Mark Sweep
Options: -XX:+UseConcMarkSweepGC
```
./gradlew gc:runApp -Dgc.objectsNumber="5000" -Dgc.jvm="-XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:ConcMarkSweepGC.log -XX:+UseConcMarkSweepGC"
```

[Log](ConcMarkSweepGC.log)

[Report](ConcMarkSweepGC.pdf)

With 4 concurrent threads:

```
./gradlew gc:runApp -Dgc.objectsNumber="5000" -Dgc.jvm="-XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:ConcMarkSweepGC_Threads_4.log -XX:+UseConcMarkSweepGC -XX:ConcGCThreads=4"
```

[Log](ConcMarkSweepGC_Threads_4.log)

[Report](ConcMarkSweepGC_Threads_4.pdf)

With -XX:+UseCMSInitiatingOccupancyOnly option:

```
./gradlew gc:runApp -Dgc.objectsNumber="5000" -Dgc.jvm="-XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:ConcMarkSweepGC_UseCMSInitiatingOccupancyOnly.log -XX:+UseConcMarkSweepGC -XX:+UseCMSInitiatingOccupancyOnly"
```

[Log](ConcMarkSweepGC_UseCMSInitiatingOccupancyOnly.log)

[Report](ConcMarkSweepGC_UseCMSInitiatingOccupancyOnly.pdf)

With -XX:+CMSScavengeBeforeRemark option:

```
./gradlew gc:runApp -Dgc.objectsNumber="5000" -Dgc.jvm="-XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:ConcMarkSweepGC_CMSScavengeBeforeRemark.log -XX:+UseConcMarkSweepGC -XX:+CMSScavengeBeforeRemark"
```

[Log](ConcMarkSweepGC_CMSScavengeBeforeRemark.log)

[Report](ConcMarkSweepGC_CMSScavengeBeforeRemark.pdf)


| GC option name | Avg Pause GC Time  | Max Pause GC Time | Duration (secs) 0 - 0.1 | Total Time | Concurrent total Time | Total created bytes |
| --- | --- | --- | --- | --- | --- | --- |
| CMS | 17 ms | 40 ms |  (319) 100.0%  | 5 sec 640 ms | 1 sec 920 ms | 23.72 gb |
| CMS with 4 threads | 16ms | 30ms | (318) 100% | 5 sec 380 ms | 1 sec 400 ms | 23.71 gb |
| CMS with UseCMSInitiatingOccupancyOnly| 19ms | 430 ms | (316) 99.685% | 6 sec 230 ms | 1 sec 910 ms | 24.9 gb |
| CMS with CMSScavengeBeforeRemark| 15 ms | 30 ms | (333) 100% | 5 sec 360 ms | 1 sec 880 ms|  20.03 gb |

### Garbage-First Garbage Collector
Options: -XX:+UseG1GC
```
./gradlew gc:runApp -Dgc.objectsNumber="5000" -Dgc.jvm="-XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:G1GC.log -XX:+UseG1GC"
```

[Log](G1GC.log)

[Report](G1GC.pdf)