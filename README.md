# Blog Application

A simple blog application built with Spring Boot.

## Features

- User registration and login
- Create, edit, delete, and view blog posts
- Comment on blog posts
- View user profiles
- Like and unlike posts
- View top liked posts
- View posts filtered by tag
- Obtain JWT tokens via `/auth/login` for stateless authentication
- Simple React UI for viewing posts
- `/post/tag/{tag}` endpoint to filter posts by a specific tag

## Technologies Used

- Spring Boot
- Spring Data JPA
- Thymeleaf
- MySQL database
- MySQL (for production)
- Spring Security
- React (frontend)

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

3. Start the React frontend (optional):
   ```sh
   cd frontend
   python -m http.server 3000
   ```
   Then open [http://localhost:3000/index.html](http://localhost:3000/index.html) in your browser.
