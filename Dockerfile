# Use OpenJDK 21 with Maven as base image
FROM maven:3.9.6-eclipse-temurin-21-alpine as build

# Set working directory
WORKDIR /app

# Copy pom.xml
COPY pom.xml .

# Copy source code
COPY src src

# Build the application with spring-boot-maven-plugin
RUN mvn clean package -DskipTests spring-boot:repackage

# Use a smaller runtime image
FROM eclipse-temurin:21-jre-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Set environment variables for database and MQ
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/ca_project
ENV SPRING_DATASOURCE_USERNAME=MaxAdmin
ENV SPRING_DATASOURCE_PASSWORD=MaxAdmin
ENV IBM_MQ_QUEUE_MANAGER=QM1
ENV IBM_MQ_CHANNEL=DEV.APP.SVRCONN
ENV IBM_MQ_CONN_NAME=ibmmq(1414)
ENV IBM_MQ_USER=app
ENV IBM_MQ_PASSWORD=passw0rd
ENV IBM_MQ_QUEUE=DEV.QUEUE.1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"] 