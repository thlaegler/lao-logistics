FROM openjdk:8-jdk-alpine

ARG SPRING_CLOUD_CONFIG_GIT_LABEL

ENV SPRING_CLOUD_CONFIG_GIT_LABEL ${SPRING_CLOUD_CONFIG_GIT_LABEL}

EXPOSE 8080

ADD target/daemon.config.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]