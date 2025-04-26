# Task Manager

A simple task management web application built with Spring Boot and PostgreSQL.

## 📦 Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring Security
- PostgreSQL
- Gradle
- DBeaver (optional DB viewer)
- Postman (for API testing)


## ⚙️ Getting Started


### 1. Clone the repository

```bash
git clone https://github.com/your-username/task-manager-v2.git
cd task-manager-v2
```


### 2. Set up PostgreSQL

Make sure PostgreSQL is installed and running:

```bash
brew install postgresql
brew services start postgresql
createdb taskmanager
```


### 3. Set up configuration

Create a `src/main/resources/application.properties` file based on the `application-example.properties` template:

```bash
cp src/main/resources/application-example.properties src/main/resources/application.properties
```
