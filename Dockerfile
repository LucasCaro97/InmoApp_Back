FROM maven:3.9.8-eclipse-temurin-17 AS builder
WORKDIR /app

# Copia el proyecto y genera el jar ejecutable
COPY pom.xml .
COPY src ./src
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia el jar generado en la etapa de build
COPY --from=builder /app/target/inmogestion-0.0.1.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
