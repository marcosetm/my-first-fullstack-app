FROM openjdk:17-jdk-slim

ENV PORT=8080

RUN useradd -ms /bin/bash appuser

WORKDIR /app

COPY /target/vacation-planner-0.0.1-RELEASE.jar app.jar

USER appuser

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
