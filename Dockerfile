FROM eclipse-temurin:21
LABEL authors="ItaloUser"

WORKDIR app
COPY /target/estuda-ai-0.0.1-SNAPSHOT.jar /app/estuda-ai.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "estuda-ai.jar"]