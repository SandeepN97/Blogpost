# Blog Application

A simple blog application built with Spring Boot.

## Features

- User registration and login
- Create, edit, delete, and view blog posts
- Comment on blog posts
- View user profiles
- Like and unlike posts
- View top liked posts
- Obtain JWT tokens via `/auth/login` for stateless authentication

## Technologies Used

- Spring Boot
- Spring Data JPA
- Thymeleaf
- MySQL database
- MySQL (for production)
- Spring Security

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven or Gradle
- Git

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/username/blog-application.git
   cd blog-application
   ```

2. Set the `JWT_SECRET` environment variable for token generation (optional):
   ```sh
   export JWT_SECRET=yourStrongSecret
   ```
