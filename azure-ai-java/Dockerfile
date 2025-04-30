# Use a base JDK image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built jar file
COPY target/azure-ai-java-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]