## Simple Payment App
The application has been developed using Spring MVC and Spring Boot using an embedded Tomcat server.

### How to run the app
1. Clone the repository and cd into the downloaded folder
2. Run `mvn clean install`
3. Run `java -jar target/mypaymentapp-0.0.1-SNAPSHOT.jar`


### Endpoints
The server listens on 8080 by default

* Pay form: /pay
* Account list: /account/list


### Some considerations
For demo purposes, the application creates an in-memory database with dummy accounts. Transactions can be added
through the application.

To simplify, services and DAO's have been created directly as classes instead of programming for interfaces.

Although the application runs in a Spring container, tests are run outside the container. One of the advantages of
running the tests outside Spring context is performance. If you run the tests frequently, starting a Spring context
every time may be frustrating. On the othe hand, running the tests inside Spring context may serve to notice if all
the beans are correctly annotated. Sometimes, you do not notice that a bean is missing from the context untill you
run the application.

In the same way, despite having decided to use Mockito to mock some dependencies, it would have been easy to mock
the dependencies manually as this is a simple application.
