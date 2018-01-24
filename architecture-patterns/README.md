# Architecture: creational, structural and behavioral patterns

Implement the following tasks with the description what benefits gives each of the implemented patter.

### Task 1: Proxy
[module](proxy/README.md)

Write a class that receives all the readPerson(String name) calls
The class should delegate the request to the DB or File if no person with the matching name was already read. Otherwise it should return a cached instance of that person.

### Task 2: Abstract Factory
[module](abstract-factory/README.md)

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

### Task 3: Visitor
[module](visitor/README.md)

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