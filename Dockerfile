FROM openjdk:21
WORKDIR /app
COPY target/inmogestion-0.0.1.jar /app/inmogestion-0.0.1.jar
ENTRYPOINT ["java", "-jar", "inmogestion-0.0.1.jar"]
