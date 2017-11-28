FROM elasticsearch:2.4.5

LABEL image "gcr.io/lao-logistics/daemon.elasticsearch:0.0.1-SNAPSHOT"
LABEL description "LAO - Elasticsearch Daemon image"
LABEL maintainer "Lao Logistics <info@example.org>"

COPY logging.yaml /usr/share/elasticsearch/config/logging.yml
COPY elasticsearch.yaml /usr/share/elasticsearch/config/elasticsearch.yml

USER root
RUN chown elasticsearch:elasticsearch config/elasticsearch.yml

USER elasticsearch
ENV PATH=$PATH:/usr/share/elasticsearch/bin
CMD ["elasticsearch"]

EXPOSE 9200
EXPOSE 9300