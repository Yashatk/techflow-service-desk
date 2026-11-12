# Build stage: compiles the jar inside Docker, no local JDK/Maven needed.
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /src
COPY pom.xml .
RUN mvn -q -B dependency:go-offline
COPY src ./src
RUN mvn -q -B -DskipTests package

FROM eclipse-temurin:24-jre
RUN groupadd --system app && useradd --system --gid app --no-create-home app
WORKDIR /app
COPY --from=build --chown=app:app /src/target/*.jar app.jar
USER app
ENV TZ=America/Sao_Paulo
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
