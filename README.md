# BookStore RESTful API
BookStore RESTful API implementation using Java / Spring Boot combined with Spring Data JPA and PostgreSQL drivers. 

# To Get Started
- We need to create a schema in your local PostgreSQL called `interbooks`
- Clone this repo
- Create `application.properties` file:
  - Create a file called `application.properties` based from [application.properties](./src/resources/application.properties)
  - Update the `xxx` in this file to the correct values for your local dev envionment
- Observe the tables in your PostgreSQL database:
```
select * from books;
select * from employees;
```
# Bookstore API Architecture
![](./docs/api-arch.png)

# Bookstore Entity Relationship Diagram (ERD)
![](./docs/bookstore-erd.png)

- The [Bookstore](./src/main/java/com/marvambi/interbooks/model/Bookstore.java) has a many-to-many relationship with the [Book](./src/main/java/com/marvambi/interbooks/model/Book.java)

Note: A bidirectional relationship is accomplished by adding a `mappedBy` attribute in the model class.

# Derived Queries
You can add these types of methods in your repository interface to query the model objects:
```
findBy<Your property>
```

# HTTP URL  & API Interop Reference
**PathVariable** 

GET http://localhost:8080/api/books/all
```
@GetMapping("api/books/{id}") 
public ResponseEntity<Book> getBookById(@PathVariable("id") Integer id) {
  // TODO...
}
```
**RequestParam**

GET http://localhost:8080/api/persons?name=fred
```
@GetMapping("api/v1/persons")
public ResponseEntity<Person> getPerson(@RequestParam(required = true) String name) {
  // TODO...
}
```
**RequestBody**

POST http://localhost:8080/api/v1/persons
```
@PostMapping("api/books/all")
public ResponseEntity<Book> getAll() {
  // TODO...
}
```

# Notes
- Collections are lazy loaded by default.
- `findbyId()` vs `getById()`
