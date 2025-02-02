FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/jojolete-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_jojolete.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_jojolete.jar"]