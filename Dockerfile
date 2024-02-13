# Start from the official OpenJDK Docker image
FROM openjdk:19-jdk-alpine

# Set a directory for the app
WORKDIR /usr/src/app

# Install bash
RUN apk add --no-cache bash

# Copy the rest of the project files into the docker image
COPY . .

RUN chmod +x ./gradlew

# Remove Windows line endings from gradlew
RUN sed -i -e 's/\r$//' gradlew

# Build the project
RUN ./gradlew build --parallel

# Run the app using gradle
CMD ["./gradlew", "run"]

# Set bash as the entrypoint
ENTRYPOINT ["bash"]

# Commands for image generation and image execution
# docker build -t java-image .
# docker run -it --rm --name java-container java-image