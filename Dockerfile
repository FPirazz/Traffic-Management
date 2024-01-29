# Start from the official OpenJDK Docker image
FROM openjdk:17-jdk-alpine

# Set a directory for the app
WORKDIR /usr/src/app

# Copy all project files into the docker image
COPY . .

# Compile the Java application
RUN javac src/main/java/App.java

# Set the command to run your Java application
CMD ["java", "-cp", "src/main/java", "App"]

# Commands for image generation and image execution
# docker build -t java-image .
# docker run -it --rm --name java-container java-image