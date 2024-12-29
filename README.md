# Tasklist Application

This project is a Tasklist application built using Spring Framework. It allows users to manage their tasks and organize them efficiently. The application provides functionalities for user authentication, task creation, updating, and deletion.

## Features

- User authentication and authorization
- Create, update, and delete tasks
- Assign tasks to users
- Retrieve tasks by user ID

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- Spring Boot

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/tasklist.git
    ```
2. Navigate to the project directory:
    ```sh
    cd tasklist
    ```
3. Build the project using Maven:
    ```sh
    mvn clean install
    ```

### Running the Application

1. Run the Spring Boot application:
    ```sh
    mvn spring-boot:run
    ```
2. The application will start on `http://localhost:8080`.

### API Endpoints

- `POST /api/v1/auth/login` - User login
- `POST /api/v1/auth/refresh` - Refresh JWT token
- `GET /api/v1/users/{id}` - Get user by ID
- `PUT /api/v1/users` - Update user
- `DELETE /api/v1/users/{id}` - Delete user
- `GET /api/v1/users/{userId}/tasks` - Get tasks by user ID
- `POST /api/v1/users/{userId}/tasks` - Create task for user
- `GET /api/v1/tasks/{id}` - Get task by ID
- `PUT /api/v1/tasks` - Update task
- `DELETE /api/v1/tasks/{id}` - Delete task

### Contributing

Contributions are welcome! Please open an issue or submit a pull request for any changes.

### License

This project is licensed under the MIT License.
