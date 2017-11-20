FROM openjdk:8-jdk-alpine

LABEL image "gcr.io/lao-logistics/daemon.eureka"
LABEL description "LAO - OAuth2 Daemon image"
LABEL maintainer "Lao Logistics <info@example.org>"

ARG SPRING_CLOUD_CONFIG_GIT_LABEL
ENV SPRING_CLOUD_CONFIG_GIT_LABEL ${SPRING_CLOUD_CONFIG_GIT_LABEL}

EXPOSE 8801

ADD target/daemon.oauth2.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]