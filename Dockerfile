# Base image with Java
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy your application JAR file
COPY target/user-management.jar /app/user-management.jar

# Expose the port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/user-management.jar"]
