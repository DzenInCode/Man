#FROM openjdk:9-alpine
FROM openjdk:11-jre-slim

EXPOSE 8080


WORKDIR /app
COPY target/football-1.0-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "/app/football-1.0-SNAPSHOT.jar"]