# BUILD
FROM maven:4.0.0-rc-5-eclipse-temurin-17 AS BUILD
LABEL authors="Daniil Futin"

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package

# RUNTIME
FROM eclipse-temurin:17-alpine AS RUNTIME

WORKDIR /app
COPY --from=BUILD /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
