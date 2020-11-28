# Simple Password API

This is a very simple project to give one password candidate is necessary check if it 
follows the minimum requirement of security. The minimum security requirement is: 
- Min length of 9 chars
- Unless 1 digit
- Unless 1 upper case letter
- Unless 1 lower case letter 
- Unless 1 one symbol
    - Possible symbols are __!@#$%^&*()-+__
    
## Documentation
The project uses Swagger framework to generate the documentation, and it can be accessed 
[here](localhost:8080/documentation).
<br>
_Available only when the project is running_
    
## Solution
The core solution is implemented using a Template Method pattern to attend all validations. 
This behavioral approach make possible to add many validations as we want, reducing the effort to 
create or remove validations and increasing this project maintainability.
<br>The project API is BFF, implemented in Micronaut 2.2.0.
<br>There is only one endpoint __POST /password__
to validate a given password and following the minimum security described above.
For this endpoint design I preferred to use POST method instead of using a GET method, 
even if GET being more RESTful in this context, to avoid security issues, 
since we are handling with sensitive information.

#### Assumptions
I admit chars with accent as different letters not symbols (_a,á,à,ã_ are different letters).

## Build
```shell script
./gradlew build
```

## Run 
Runs using the default port (8080)
```shell script
./gradlew run
```

### Example request
```shell script
 curl --location --request POST 'localhost:8080/password' \
     --header 'Content-Type: application/json' \
     --data-raw '{
         "password": "[PASSWORD]",
         "detailed": [true/false] // Optional parameter
     }'
```

## Project Structure
- Java 11
- Micronaut 
- JUnit5
- Swagger
    