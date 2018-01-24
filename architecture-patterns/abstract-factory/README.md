# Task 1: Proxy

Write a class that receives all the readPerson(String name) calls
The class should delegate the request to the DB or File if no person with the matching name was already read. Otherwise it should return a cached instance of that person.

## Getting Started

### Prerequisites

* [Gradle](https://gradle.org/) - Dependency Management, [how to install](https://gradle.org/install/)
* [Lombok](https://projectlombok.org/) - Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java
[How to install IDEA](https://projectlombok.org/setup/intellij) and [How to install gradle](https://projectlombok.org/setup/gradle)

### Run application

Run application in file mode.
If `cdp.file` is specified and file exist,
then all information will be loaded from this file.
If file doesn't exist, then nothing will be load and
all information from application will be saved to this file.
`There is only json file support`.

```
./gradlew architecture-patterns:abstract-factory:runApp -Dcdp.file="super.json"
```

Database mode. When `cdp.file` doesn't specified, application will start
with embedded database(current: h2)
```
./gradlew architecture-patterns:abstract-factory:runApp
```

## Running the tests

Explain how to run the automated tests for this system

### Unit tests


```
./gradlew architecture-patterns:abstract-factory:test
```

## Built With

* [Gradle](https://gradle.org/) - Dependency Management

## Authors

* **Siarhei Blashuk** - *Developer* - [BSGfB](https://github.com/BSGfB)
