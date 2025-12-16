FROM maven:3.9.6-eclipse-temurin-22 AS builder

WORKDIR /app

COPY app .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:22-jdk-alpine

COPY --from=builder /app/target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
