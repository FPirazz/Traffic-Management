FROM gradle:8.2.0-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

EXPOSE 9085
EXPOSE 9092

RUN gradle wrapper
RUN ./gradlew build --parallel

FROM openjdk:19

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-db.jar

ENTRYPOINT ["java", "-jar", "/app/spring-db.jar"]