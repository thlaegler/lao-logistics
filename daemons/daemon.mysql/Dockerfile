FROM mysql:5.7

LABEL image "gcr.io/example/example.mysql:0.0.1-SNAPSHOT"
LABEL description "example mysql image"
LABEL maintainer "John Doe <john.doe@example.org>"

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=example
ENV MYSQL_USER=test
ENV MYSQL_PASSWORD=test

ADD init.sql /docker-entrypoint-initdb.d/init.sql

EXPOSE 3306