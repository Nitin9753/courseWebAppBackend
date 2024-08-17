
FROM maven:3.9.5-eclipse-temurin-17 AS builder

# Setting up the working directory
WORKDIR /app

# Copying the pom.xml file and downloading the dependencies
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copying the source code
COPY src ./src

# Building the application
RUN mvn clean package -DskipTests

# Running the application
FROM eclipse-temurin:17-jdk-jammy

# Setting the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/courseWebApp.jar /app/courseWebApp.jar

# Exposing the port that the spring boot will run on to run it on local host
EXPOSE 8080

# Set environment variables of the mongoDB
ENV SPRING_DATA_MONGODB_HOST=mongodb
ENV SPRING_DATA_MONGODB_PORT=27017

# Running the Spring Boot application
ENTRYPOINT ["java", "-jar", "courseWebApp.jar"]
