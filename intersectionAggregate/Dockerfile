FROM gradle:8.6.0-jdk21-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle wrapper
RUN ./gradlew build --parallel

# Start from the official OpenJDK Docker image
FROM openjdk:19-jdk-alpine

# Set a directory for the app
WORKDIR /app

# Expose port 9080
EXPOSE 9080

# Copy the source code of the builded project from the former stage (not the jar)
COPY --from=build /home/gradle/src/ /app/

# Run the Gradle task runAgents
CMD ["./gradlew", "runAgents"]

# docker build -t name-of-image .
# docker run -it --rm --name name-of-container name-of-image
# docker run -it --rm --name name-of-container -p 9080:9080 name-of-image