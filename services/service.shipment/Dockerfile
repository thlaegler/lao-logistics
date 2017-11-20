FROM openjdk:8-jdk-alpine

LABEL image "gcr.io/lao-logistics/service.shipment"
LABEL description "LAO - Shipment Service image"
LABEL maintainer "Lao Logistics <info@example.org>"

ARG SERVER_PORT=8080
ARG SPRING_CLOUD_CONFIG_GIT_URI=http://daemon.config:8888
ARG SPRING_CLOUD_CONFIG_GIT_LABEL=master

ENV SERVER_PORT ${SERVER_PORT}
ENV SPRING_CLOUD_CONFIG_GIT_URI ${SPRING_CLOUD_CONFIG_GIT_URI}
ENV SPRING_CLOUD_CONFIG_GIT_LABEL ${SPRING_CLOUD_CONFIG_GIT_LABEL}

EXPOSE ${SERVER_PORT}

ADD target/service.shipment.jar /app.jar

ENTRYPOINT ["java", "-jar", "-Dserver.port=${SERVER_PORT}", "-Dspring.cloud.config.uri=${SPRING_CLOUD_CONFIG_GIT_URI}", "-Dspring.cloud.config.label=${SPRING_CLOUD_CONFIG_GIT_LABEL}","/app.jar"]