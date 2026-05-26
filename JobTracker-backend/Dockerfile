# ===========================
# Stage 1: Build
# ===========================
# Use Maven with Eclipse Temurin JDK 21 to build the application
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Set working directory inside container
WORKDIR /app

# Copy only pom.xml first to leverage Docker cache for dependencies
COPY pom.xml .

# Download all dependencies offline (speeds up subsequent builds if pom.xml unchanged)
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Build the application and package it, skipping tests for faster build
RUN mvn clean package -DskipTests

# ===========================
# Stage 2: Runtime
# ===========================
# Use lightweight Eclipse Temurin JDK 21 runtime image
FROM eclipse-temurin:21-jdk

# Set working directory for runtime
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8080

# Entry point to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]