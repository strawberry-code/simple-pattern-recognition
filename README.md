# Geometric Plane Management System

## Overview
This repository contains a Java-based system for managing geometric points on a plane. It provides functionalities to add and connect points, retrieve details about points and their connections, and perform operations like clearing all points from the plane. The system is designed to be interacted with through a RESTful API, making it suitable for integration into larger software architectures or for use in educational settings to demonstrate geometric concepts.

## Features
- **Point Management**: Add and retrieve points with their x and y coordinates.
- **Connection Management**: Automatically manage connections between points when added to the plane.
- **RESTful API**: Interact with the geometric plane through a well-defined REST API.
- **Error Handling**: Comprehensive error handling across the API to ensure stability and reliability.

## Components
- `Point`: A class representing a point in a geometric plane.
- `Plane`: A singleton class that manages a collection of points and their connections.
- `ApiResponse`: A generic class for formatting API responses with a consistent structure.
- `PlaneController`: A Spring Boot controller that provides RESTful endpoints for managing points on the plane.
- `PointRequest`: A simple class used for handling point data from API requests.

## API Endpoints
- **POST /point**: Add a new point to the plane.
- **GET /point?id={id}**: Retrieve a specific point by its ID.
- **GET /lines/{n}**: Retrieve combinations of points that form lines with at least 'n' points.
- **GET /space**: Retrieve all points on the plane.
- **DELETE /space**: Clear all points from the plane.

## Usage
To start using the Geometric Plane Management System, clone the repository and build the project using Maven:

```bash
git clone [repository-url]
cd [repository-directory]
mvn install
mvn spring-boot:run