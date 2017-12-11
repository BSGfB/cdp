# Todo list application
Trello card: https://trello.com/c/wwwtU7K3

### Primary goals is
Create console application to manage todo list.

##### Supported operation:
1. Add todo
2. Remove one
3. Remove all
4. Find one
5. Find all

### Haw to start
Start with gradle:
```
gradle todo-list:clean todo-list:build todo-list:runApp
```
or
```
./gradlew todo-list:clean todo-list:build todo-list:runApp
```

if gradle disturbs normal application flow, then run:
```
./gradlew todo-list:clean todo-list:build todo-list:fatJar
java -jar "./todo-list/build/libs/todo-list-1.0-SNAPSHOT.jar"
```

Only linux:
```
./todo-list/start.sh
```

