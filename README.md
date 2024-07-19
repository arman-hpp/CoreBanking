# Tosan-Academy-Final-Java-Project

### About
This repository has been developed by `Arman Hassanpour` to fulfill the final project of Java course by `Ali Gholami` at `Tosan Academy`. It is subject to submitted documents.
To develop the project, Spring Boot 3 has been used. The project is compatible with Java 17 and can be run on JDK 18.\
\
This project is a banking software to managing customers and accounts and also has the capability to provide financial facilities to bank customers. Banking software is very extensive and comprehensive, usually following many accounting principles. However, in this project, apart from the method of calculating installments, no specific accounting principle has been used. Additionally, the rules for facilities in Islamic banking are very complex, which have not been applied in this project.
\
The overall scenario of the intended system is as follows: a bank user can define customers in the software and open accounts for them. Then, based on customer requests, they can create loan files and deposit the loan amount into the customer's account. Subsequently, according to the created installment schedule, they can collect monthly installments from the customer until the entire loan is settled.
\
Besides the main scenario, the system also provides features such as reporting to manage the bank's capital for the bank manager. The system includes two types of users:
\
* User (bank employee) - who performs customer-related operations such as opening accounts and creating loan files.</p></li>
* Manager (branch manager) - who performs managerial operations such as reporting or allocating budgets for providing facilities to customers.</p></li>

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
