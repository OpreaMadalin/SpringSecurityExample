# SpringSecurityExample

Example project of using Spring Security

Used Tools:
- Java
- Spring Boot
- Spring Security
- Mapstruct
- MySQL
- Lombok
- Liquibase

To start the project:
- Install MySQL Server on your local machine
- Go to /application.db.properties and update
username and password with your credentials
- Hit the run button

To use project:
- Use Postman or alternatives for sending requests 
to API, send requests to endpoints from controller package

  * EX:

      GET :
      http://localhost:8080/auth/register
      BODY:
  * 
    {
          "username" : "myUsername",
          "password" : "myPassword",
          "email" : "myEmail"
    }
