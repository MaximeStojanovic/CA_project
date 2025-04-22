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
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- IBM MQ
- Log4j2
- JUnit 5
- Bootstrap 5 (for web interface)

## Getting Started

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