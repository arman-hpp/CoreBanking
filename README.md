# Tosan-Academy-Final-Java-Project

### About
This repository has been developed by `Arman Hassanpour` to fulfill the final project of Java course by `Ali Gholami` at `Tosan Academy`. It is subject to submitted documents.
To develop the project, Spring Boot 3 has been used. The project is compatible with Java 17 and can be run on JDK 18.\
\
The project is a multi-module project. It has been built from a parent pom that manages a group of submodules. This project has 6 modules named `utils`,`model`,`repository`,`loan`,`core_banking`,`application`.
When a multi-module project is run, all the modules are deployed together; furthermore an individual module can be deployed.


#### Utils module
The Utils module contains helpers which are being used throughout the entire application.

#### Model module
The Model module contains Entities which have been used in the project.

#### Repository module
The Repository module contains repositories which have been used in the project. It depends on the Model module.

#### Service module (core_banking and loan)
The Service module implements the services. It depends on Repository module, Model module and Utils module. According to the documentation of the project, this module has been broken into two modules named `core_banking` and `loan`.

#### Application module
The application module is the main module of the project. It contains the application class in which the main method is defined that is necessary to run the Spring Boot Application. It also contains application configuration properties, controllers, views, and resources.
The Application module includes Service Implementation modules (core_banking and loan) as dependency that contains Model module, Repository module, and Utils module.


### Prerequisites
* Maven
* Git
* JDK 22.0.2

### Dependencies
* [Spring Boot Starter](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter)
* [Spring Boot Starter Data JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
* [Spring Boot Starter Test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test)
* [Spring Boot Starter Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
* [Spring Boot Starter Security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security)
* [Spring Boot Maven Plugin](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-maven-plugin)
* [Spring Boot Starter Thymeleaf](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-thymeleaf)
* [Thymeleaf Extras Spring Security 6](https://mvnrepository.com/artifact/org.thymeleaf.extras/thymeleaf-extras-springsecurity6)
* [Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok)
* [Model Mapper](https://mvnrepository.com/artifact/org.modelmapper/modelmapper)
* [H2 Database Engine](https://mvnrepository.com/artifact/com.h2database/h2)
* [Apache POI Common](https://mvnrepository.com/artifact/org.apache.poi/poi)
* [Apache Commons CSV](https://mvnrepository.com/artifact/org.apache.commons/commons-csv)
* [IText Core](https://mvnrepository.com/artifact/com.itextpdf/itextpdf)

### Installation
* `git clone https://github.com/arman-arian/Tosan-Academy-Final-Java-Project.git`
* `cd Tosan-Academy-Final-Java-Project`
* `$mvnw install`

### Testing (Compile + Testing)
* `$mvnw clean test`

### Start Spring boot Application
* `$mvnw spring-boot:run`

### Package (Compile + Testing + Create JAR file)
* `$mvnw clean package`

### Start Spring boot Application with JAR file
* `$mvnw clean package`
* `$java -jar target/<filename>.jar`

### Test Users
| Username  | Password  | UserType  |
|-----------|-----------|-----------|
| admin     | 12345     | Admin     |
| arman     | 12345     | User      |
| ali       | 12345     | Admin     |

### Database Users
| Username | Password  |
|----------|-----------|
| sa       | 12345     |
