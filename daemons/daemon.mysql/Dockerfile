FROM mysql:5.7

LABEL image "gcr.io/lao-logistics/daemon.mysql:0.0.1-SNAPSHOT"
LABEL description "LAO - MySQL Daemon image"
LABEL maintainer "Lao Logistics <info@example.org>"

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=example
ENV MYSQL_USER=test
ENV MYSQL_PASSWORD=test

ADD init.sql /docker-entrypoint-initdb.d/init.sql

EXPOSE 3306