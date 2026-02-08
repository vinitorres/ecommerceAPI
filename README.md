# Ecommerce API (Backend)

This project is an e-commerce API developed in **Kotlin** with **Spring Boot**. It was created as a study and testing environment for development assisted by **Artificial Intelligence** tools.

## 🛠 Tech Stack
- **Language:** [Kotlin](https://kotlinlang.org/)
- **Framework:** [Spring Boot 3](https://spring.io/projects/spring-boot)
- **Security:** [Spring Security](https://spring.io/projects/spring-security) & [JWT (JSON Web Token)](https://jwt.io/)
- **Persistence:** Spring Data JPA / Hibernate
- **Database:** H2 Database (In-Memory for development)
- **Build Tool:** Gradle

## 🏗 Architecture and Patterns
The project follows **Clean Architecture** and **Separation of Concerns** principles, organized into the following layers:

- **Domain:** Contains business entities (Models) and repository interfaces. It is the innermost and independent layer.
- **Application:** Implements business logic through **Use Cases**, ensuring each system operation is isolated and testable.
- **Infrastructure:** Responsible for technical implementations such as security configurations (JWT), filters, persistence, and connectivity.
- **Presentation:** The API entry layer, containing REST Controllers, Request, and Response DTOs.

### Design Patterns Used:
- **Repository Pattern:** Decoupling persistence logic from the domain.
- **Use Case Pattern:** Encapsulation of specific business rules.
- **Dependency Injection:** Managing dependencies through Spring IoC.
- **RESTful API:** Endpoint design following HTTP standards.

---
*This project is strictly for learning and technological experimentation purposes.*
