# build
FROM gradle:jdk17-alpine AS build
LABEL authors="Daniil Futin"

WORKDIR /app

COPY build.gradle .
COPY settings.gradle .

RUN gradle dependencies

COPY src ./src

RUN gradle clean build -x test --no-daemon

# runtime
FROM eclipse-temurin:17-alpine AS runtime

WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
