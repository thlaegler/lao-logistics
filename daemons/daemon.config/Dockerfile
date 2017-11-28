FROM openjdk:8-jdk-alpine

LABEL image "gcr.io/lao-logistics/daemon.config"
LABEL description "LAO - Cloud Config Daemon image"
LABEL maintainer "Lao Logistics <info@example.org>"

ARG SPRING_CLOUD_CONFIG_GIT_LABEL
ENV SPRING_CLOUD_CONFIG_GIT_LABEL ${SPRING_CLOUD_CONFIG_GIT_LABEL}

EXPOSE 8080

ADD target/daemon.config.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]