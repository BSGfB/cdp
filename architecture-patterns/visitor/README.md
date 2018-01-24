# Task 3: Visitor

Create an Employee class with the following attributes:
- Name
- Salary
- Department

Create a Company class that holds a collection of Employees
Company must provide the following:
- Total salaries computation
- Number of employees
- Average salary
- Number of employees per department
- Salary raise (by percent)
Implement all Company activities via Visitors

## Getting Started

### Prerequisites

* [Gradle](https://gradle.org/) - Dependency Management, [how to install](https://gradle.org/install/)
* [Lombok](https://projectlombok.org/) - Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java
[How to install IDEA](https://projectlombok.org/setup/intellij) and [How to install gradle](https://projectlombok.org/setup/gradle)

### Run application
Command bellow runs application, which creates couple employees
and runs several operation according visitor pattern
```
./gradlew architecture-patterns:visitor:runApp
```

## Running the tests

Explain how to run the automated tests for this system

### Unit tests

```
./gradlew architecture-patterns:visitor:test
```

## Built With

* [Gradle](https://gradle.org/) - Dependency Management

## Authors

* **Siarhei Blashuk** - *Developer* - [BSGfB](https://github.com/BSGfB)
