FROM redis:4.0

LABEL image "gcr.io/example/example.redis:0.0.1-SNAPSHOT"
LABEL description "example redis image"
LABEL maintainer "John Doe <john.doe@example.org>"

COPY redis.conf /etc/redis/redis.conf

VOLUME ["/data"]
WORKDIR /data

CMD ["/usr/bin/redis-server", "/etc/redis/redis.conf"]

EXPOSE 6379

ENTRYPOINT ["/usr/bin/redis-server"]