#  Сar - service 🚗

### 🔧 Project description:
This is a restful web-application that represents a service for car repair. 
The app is implemented using n-tier architectural framework, built 
on Spring Boot using Spring Data, Spring Web, and Hibernate. The application 
is connected to the Postgres database.

### 🔧 Features:
The ability to create an order that contains the necessary services and 
parts for the car. Calculate the cost of the order according to the discount 
system. Calculate the salary of the mechanics who performed the car repair.

You can see the available actions at `http://localhost:6868/swagger-ui.html`
You can make operations by moving around endpoints.

### 🔧 Structure
> The project consists of three layers:
* __Controller__ - provides for the processing of user requests
* __Service__ - provides all the business-logic;
* __Repository__ - provides all database operations;

### 🔧 Application was built with:
* Java 11;
* Apache Maven;
* Spring Boot (Spring Web, Spring Data)
* Hibernate, Jpa;
* Swagger
* PostgresDB
