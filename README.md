
# 🛡️ Spring Boot Admin Dashboard

A secure and efficient Admin Dashboard built using **Java Spring Boot**, featuring **role-based authentication**, **user management**, and **product management**. Designed to handle administrative tasks with a focus on clean architecture, security, and scalability.

---

## 📌 Features

### 🔐 Authentication & Authorization
- Login and logout functionality.
- Role-Based Access Control (RBAC) using Spring Security:
  - `ADMIN`: Full access to all modules.
  - `USER`: Limited access (view-only or restricted actions).

### 👤 User Management
- Create new users with assigned roles.
- Update user information and roles.
- Delete existing users.
- View all users in a structured table.

### 📦 Product Management
- Add new products with attributes like name, stock, price, etc.
- Update existing product details.
- Delete products from inventory.
- List and search products with pagination.

---

## 🛠️ Technologies Used

| Technology       | Purpose                            |
|------------------|------------------------------------|
| Java 17+         | Main programming language          |
| Spring Boot      | Backend framework                  |
| Spring Security  | Authentication and authorization   |
| JPA / Hibernate  | ORM for database operations        |
| MySQL            | Relational database                |
| Maven            | Build and dependency management    |
| Thymeleaf (optional) | Frontend templating engine     |

---

## 📁 Project Structure

```
src
└── main
    ├── java
    │   └── com.learn.adminpanel
    │       ├── controller       # REST endpoints
    │       ├── model            # Entity classes
    │       ├── repository       # JPA repositories
    │       ├── service          # Business logic
    │       └── security         # Security config & filters
    └── resources
        ├── templates/           # Thymeleaf HTML views (if used)
        └── application.properties
```

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL database
- Git

### Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/abhikanjia/admin_panel.git
   ```

2. **Configure your database** in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/yourdb
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Build and run the project**:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the dashboard**:
   ```
   http://localhost:8080/
   ```

---

> You can change or seed the users from the database or initialization scripts.

---

## 🙋‍♂️ Author

**Abhi Kanjia**  
GitHub: [@abhikanjia](https://github.com/abhikanjia)  
Email: kanjiaabhi@gmail.com
