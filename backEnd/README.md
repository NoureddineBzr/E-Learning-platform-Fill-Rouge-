# E-Learning Platform - Backend

## Description
This is the backend part of the E-learning platform developed by **Noureddine Bouzire** (email: bouzire.noureddine1@gmail.com) using **Spring Boot**. The backend provides a RESTful API to manage users, courses, and chapters, and it implements security features such as authentication and authorization using **JWT** with **Spring Security**.

## Features
- **User management** (Admin, Professors, Students)
- **Course and chapter management**
- **Secure authentication and authorization** (JWT, Spring Security)
- **Role-based access control (RBAC)**
- **Database integration with MySQL**
- **Unit testing with JUnit and Mockito**
- **Continuous integration with Jenkins and SonarQube**

## Technologies Used
- **Spring Boot**
- **Spring Security (JWT)**
- **Hibernate / JPA**
- **Maven**
- **MySQL**
- **JUnit & Mockito** (for testing)
- **SonarQube** (for code quality analysis)
- **Docker** (for containerization)
- **Jenkins** (for continuous integration)

## Prerequisites
- **Java 17+** installed
- **Maven 3.6+** installed
- **MySQL 8.0+** installed and running
- **Docker** installed (optional, for containerization)
- **Jenkins** and **SonarQube** set up (optional, for CI/CD)

## MySQL Database Setup
1. Install **MySQL** and create a new database:
   ```sql
   CREATE DATABASE elearning_db;

