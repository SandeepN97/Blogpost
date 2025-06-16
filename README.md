# Blog Application

A simple blog application built with Spring Boot.

## Features ✨

### Backend
- 🔐 **User registration and login**
- 📝 **Create, edit, delete, and view blog posts**
- 💬 **Comment on blog posts**
- 👤 **View user profiles**
- 👍 **Like and unlike posts**
- 📈 **View top liked posts**
- 📌 **Bookmark favorite posts**
- 🔖 **View posts filtered by tag**
- 🔍 **Search posts by title or content**
- 🛡️ **Obtain JWT tokens** via `/auth/login` for stateless authentication

### Frontend
- 🖥️ **Interactive React UI** for browsing and liking posts with tag filtering
- ⭐ **View top liked posts** from the React UI
- ✍️ **Create posts with Markdown** formatting in the React UI

### API Endpoints
- `/post/tag/{tag}` endpoint to filter posts by a specific tag
- `/post/search?q=keyword` endpoint to search posts
- `/post/{postId}/comments` endpoint to view or add comments on a post
- `/user/{userId}/bookmark/{postId}` endpoint to bookmark or remove a post
- `/user/{userId}/bookmarks` endpoint to list a user's bookmarks

## Technologies Used

- Spring Boot
- Spring Data JPA
- Thymeleaf
- H2 in-memory database
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
   git clone https://github.com/username/Blogpost.git
   cd Blogpost
   ```

2. Set the `JWT_SECRET` environment variable for token generation (optional):
   ```sh
   export JWT_SECRET=yourStrongSecret
   ```

3. Start the React frontend (optional):
   Once the `ui` helper is on your `PATH` you can run it from anywhere:
   ```sh
   ui       # or UI

   ```
   The script serves the `frontend` folder on [http://localhost:3000](http://localhost:3000)
   by auto-detecting `python3`, `python` or `npx http-server`. You can override the
   command completely by setting the `FRONTEND_CMD` environment variable (e.g. `npm run dev`).

   To make the command globally available on macOS/Linux, create symlinks:
   ```sh
   sudo ln -s "$PWD/ui" /usr/local/bin/ui
   sudo ln -s "$PWD/ui" /usr/local/bin/UI
   ```
   The script resolves its own path so these symlinks work from any directory.
   Alternatively add aliases to your `~/.bashrc` or `~/.zshrc`:
   ```sh
   echo "alias ui='$PWD/ui'" >> ~/.bashrc
   echo "alias UI='$PWD/ui'" >> ~/.bashrc
   # reload the shell for the aliases to take effect
   ```

4. No external database setup is required. The application uses an embedded
   H2 database that runs in memory. You can access the H2 console at
   [http://localhost:8084/blogpostapplication/h2-console](http://localhost:8084/blogpostapplication/h2-console)
   after starting the backend.

### Running the Application 🚀

1. From the project root, start the Spring Boot backend:
   ```sh
   ./mvnw spring-boot:run
   ```
   The API will be available at [http://localhost:8084/blogpostapplication](http://localhost:8084/blogpostapplication).
   The first run may take a while as Maven downloads dependencies.

   If you want to prefetch all dependencies manually, run:
   ```sh
   ./mvnw dependency:resolve
   ```
   This command uses the [maven-dependency-plugin](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-dependency-plugin) to ensure all required artifacts are downloaded.

2. *(Optional)* Execute the test suite:
   ```sh
   ./mvnw test
   ```

### Running the Application 🚀

1. From the project root, start the Spring Boot backend:
   ```sh
   ./mvnw spring-boot:run
   ```
   The API will be available at [http://localhost:8084/blogpostapplication](http://localhost:8084/blogpostapplication).
   The first run may take a while as Maven downloads dependencies.

   If you want to prefetch all dependencies manually, run:
   ```sh
   ./mvnw dependency:resolve
   ```
   This command uses the [maven-dependency-plugin](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-dependency-plugin) to ensure all required artifacts are downloaded.

2. *(Optional)* Execute the test suite:
   ```sh
   ./mvnw test
   ```


### API Documentation

After starting the Spring Boot application, you can explore all REST endpoints interactively using Swagger UI. Visit:

```
http://localhost:8084/blogpostapplication/swagger-ui/index.html
```

Swagger resources are publicly accessible, so no authentication is required to browse the docs. The generated OpenAPI specification is also available at `/blogpostapplication/v3/api-docs` and can be used to generate client libraries.


