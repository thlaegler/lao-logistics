FROM redis:4.0

LABEL image "gcr.io/lao-logistics/service.redis:0.0.1-SNAPSHOT"
LABEL description "example redis image"
LABEL maintainer "Lao Logistics <info@example.org>"

COPY redis.conf /etc/redis/redis.conf

VOLUME ["/data"]
WORKDIR /data

CMD ["redis-server", "/etc/redis/redis.conf"]

EXPOSE 6379

ENTRYPOINT ["redis-server"]