gradle clean build fatJar

SCRIPTPATH="$( cd "$(dirname "$0")" ; pwd -P )"
java -jar "$SCRIPTPATH/build/libs/todo-list-1.0-SNAPSHOT.jar"
