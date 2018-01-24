# Task 2: Abstract Factory

Write a program that supports writing and reading from files and DB (Access DB using JDBC.ODBC)
Writing to a file includes these features:
- Defining the file name to write or read from
- Wrapping with a buffer
- Writing/Reading Persons
- Writing to the DB is also done in three steps:
- Loading driver and creating connection
- Person to DB serializer which breaks Objects into Record and vise versa
- Writing/Reading Persons

Client chooses to work with files or DB but once the choice was made – client code is identical in both cases. This means that and beside specifying the source (File/DB) working with the actual resource should be transparent and includes the following operations:
- void writePerson (Person)
- Person readPerson()
- Person readPerson (String name)

## Getting Started

### Prerequisites

* [Gradle](https://gradle.org/) - Dependency Management, [how to install](https://gradle.org/install/)
* [Lombok](https://projectlombok.org/) - Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java
[How to install IDEA](https://projectlombok.org/setup/intellij) and [How to install gradle](https://projectlombok.org/setup/gradle)

### Run application
First requests will pass through CachePersonDapProxy, then JdbcPersonDap.
After that, requested persons stored in cache and database will not be called.
`Pay attention to logs`
```
./gradlew architecture-patterns:proxy:runApp
```


## Running the tests

Explain how to run the automated tests for this system

### Unit tests

```
./gradlew architecture-patterns:proxy:test
```

## Built With

* [Gradle](https://gradle.org/) - Dependency Management

## Authors

* **Siarhei Blashuk** - *Developer* - [BSGfB](https://github.com/BSGfB)
