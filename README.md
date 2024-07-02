# Geometric Space Management System

## Overview
This repository contains a Java-based system for managing geometric points on a space. It provides functionalities to add and connect points, retrieve details about points and their connections, and perform operations like clearing all points from the space. The system is designed to be interacted with through a RESTful API, making it suitable for integration into larger software architectures or for use in educational settings to demonstrate geometric concepts.

## Features
- **Point Management**: Add and retrieve points with their x and y coordinates.
- **Connection Management**: Automatically manage connections between points when added to the space.
- **RESTful API**: Interact with the geometric space through a well-defined REST API.
- **Error Handling**: Comprehensive error handling across the API to ensure stability and reliability. 

### Combination Calculation Strategies
The system supports two strategies for calculating combinations of points that form lines with at least 'n' points:
- **Iterative**: Utilizes an iterative approach to generate combinations based on the binomial coefficient. This method is efficient for large values of 'n'.
- **Recursive**: Employs a recursive strategy to compute combinations, also based on the binomial coefficient. This method is more memory-intensive but can be more straightforward to implement.

## Technologies
- **Java**: The core programming language used to implement the system.
- **Spring Boot**: A Java-based framework for building web applications and RESTful services.
- **Maven**: A build automation tool used for managing dependencies and building the project.
- **JUnit 5**: A testing framework used for unit testing the system components.
- **Mockito**: A mocking framework used to create mock objects for testing purposes.
- **Javadoc**: A tool used to generate API documentation for the system.
- **Swagger**: A tool used to generate interactive API documentation for the system.
- **Postman**: A tool used to test and interact with the RESTful API endpoints.
- **IntelliJ IDEA**: An integrated development environment used for developing and testing the system.
- **Markdown**: A lightweight markup language used to write documentation for the project.

## Components
- `Point`: A class representing a point in a geometric space.
- `Space`: A singleton class that manages a collection of points and their connections.
- `ApiResponse`: A generic class for formatting API responses with a consistent structure.
- `SpaceController`: A Spring Boot controller that provides RESTful endpoints for managing points on the space and changing the combination calculation mode.
- `PointRequest`: A simple class used for handling point data from API requests.

## API Endpoints
- **POST /point**: Add a new point to the space.
- **GET /point?id={id}**: Retrieve a specific point by its ID.
- **GET /lines/{n}**: Retrieve combinations of points that form lines with at least 'n' points.
- **GET /space**: Retrieve all points on the space.
- **DELETE /space**: Clear all points from the space.
- **GET /changemode**: Retrieves the current combination mode used for calculating combinations. This endpoint is useful for clients to verify the current mode without making changes.
- **POST /changemode**: Allows the client to switch the combination mode between ‘iterative’ and ‘recursive’ (param `mode`). This feature is designed to enable dynamic switching based on performance needs or computational constraints.

## Usage
To start using the Geometric Space Management System, clone the repository and build the project using Maven:

```bash
git clone [repository-url]
cd [repository-directory]
mvn install
mvn spring-boot:run
```

The system will start on `http://localhost:8080` by default. You can interact with the API using tools like Postman or Swagger UI. The API documentation can be accessed at `http://localhost:8080/swagger-ui.html`.

JavaDoc documentation has been generated for the project and can be found in the `apidocs` directory. Open `index.html` in a web browser to view the API documentation.