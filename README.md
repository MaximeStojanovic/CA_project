# Message Queue Application

A Spring Boot application for managing message queues and partner communications, developed in collaboration between Maxime and Cursor AI.

## Features

### Message Management
- Process and store messages with status tracking (RECEIVED, PROCESSED, ERROR)
- View message history and details
- Real-time message status updates

### Partner Management
- Create and manage communication partners
- Configure partner-specific settings:
  - Direction (INBOUND/OUTBOUND)
  - Flow Type (MESSAGE, ALERTING, NOTIFICATION)
  - Application integration
  - Custom descriptions

### Technical Features
- RESTful API endpoints for all operations
- Asynchronous processing using CompletableFuture
- PostgreSQL database integration
- IBM MQ integration for message queuing
- Comprehensive logging with Log4j2
- JUnit test coverage for critical components

## Technology Stack
- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- IBM MQ
- Log4j2
- JUnit 5
- Bootstrap 5 (for web interface)
- Docker & Docker Compose

## Getting Started

### Prerequisites
- Docker and Docker Compose installed
- Git for cloning the repository

### Running with Docker

1. Clone the repository:
```bash
git clone <repository-url>
cd <repository-directory>
```

2. Build and start all services:
```bash
docker-compose up --build
```

This will start:
- The Spring Boot application on port 8080
- PostgreSQL database on port 5432
- IBM MQ server on ports 1414 and 9443

3. Access the application:
- Web interface: http://localhost:8080
- API endpoints: http://localhost:8080/api

4. To stop the services:
```bash
docker-compose down
```

### Manual Setup (Alternative)

1. Clone the repository
2. Configure your database settings in `application.properties`
3. Set up IBM MQ connection details
4. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```

## API Endpoints

### Messages
- `GET /messages` - Get all messages
- `GET /messages/{id}` - Get message by ID
- `GET /messages/process` - Process a new message

### Partners
- `GET /partners` - Get all partners
- `GET /partners/{id}` - Get partner by ID
- `POST /partners/create` - Create a new partner
- `DELETE /partners/delete/{id}` - Delete a partner

## Development Notes

This project was developed as a collaborative effort between Maxime and Cursor AI, combining human expertise with AI-powered development assistance. The collaboration focused on:

- Clean architecture implementation
- Best practices in Spring Boot development
- Comprehensive test coverage
- Efficient message queue handling
- User-friendly web interface

## License

This project is licensed under the MIT License - see the LICENSE file for details. 