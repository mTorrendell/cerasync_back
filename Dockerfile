# Use Java 17 base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Give execute permission to Maven wrapper
RUN chmod +x ./mvnw

# Download dependencies (this layer will be cached)
RUN ./mvnw dependency:go-offline -DskipTests

# Copy source code
COPY src ./src

# Build the application (skip tests to avoid needing database during build)
RUN ./mvnw clean package -DskipTests

# Expose port (Render will set this via $PORT)
EXPOSE 8080

# Use shell form to allow variable expansion
CMD java -Dserver.port=$PORT -jar target/cerasync_back-0.0.1-SNAPSHOT.jar