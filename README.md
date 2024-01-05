# ioBuildersBankPoC

ioBuilders tech PoC

## Api Docs

http://localhost:8080/swagger-ui/index.html

In order to use the swagger endpoints you must provide a valid token

## Security
Note all api endpoints are secured using spring security except `/users/registry` and `login`, in order to operate with the remainings operations you must include the bearer token included in the login response

## Postman collection

A fully postman collection is available under `src/test/resources/` or simply [here](https://github.com/afuentecruz/ioBuilders-banking-poc/blob/main/src/test/resources/ioBuilders%20banking%20PoC.postman_collection.json)

Please, not that the bearer token managed through a script after login request and setted in an environment variable for both initial users (A & B)

## Database

This service uses H2 for persistence, for data visualization visit http://localhost:8080/h2-console

```
user: admin
password: admin 
jdbc-url: jdbc:h2:mem:db
```
