# springboot-integration-docker

## Build images
```
cd producer
mvn package docker:build -DpushImage
cd ../consumer
mvn package docker:build -DpushImage
```
# Dockerise
```
docker run -d --hostname rabbitmq --name rabbitmq -p 4369:4369 -p 5671:5671 -p 5672:5672 -p 25672:25672 rabbitmq:3
docker inspect --format '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' rabbitmq
docker logs rabbitmq

docker run -d --hostname producer -e SPRING_RABBITMQ_ADDRESSES="amqp://guest:guest@rabbitmq:5672" --name producer --link rabbitmq:rabbitmq -p 8000:8000 amanly/producer
docker inspect --format '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' producer
docker logs producer
docker exec -i -t producer /bin/sh
curl -d {} http://localhost:8000/greet/hello

docker run -d --hostname consumer -e SPRING_RABBITMQ_ADDRESSES="amqp://guest:guest@rabbitmq:5672" --name consumer --link rabbitmq:rabbitmq amanly/consumer
docker inspect --format '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' consumer
docker logs consumer
docker exec -i -t consumer /bin/sh
```
