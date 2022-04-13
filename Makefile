# Requires make
# choco install make -y

.PHONY: java-build
java-build:
	.\weather\gradlew build -p .\weather\ 


.PHONY: docker-build
docker-build:
	docker build -t weather-service-img . -f .\weather\Dockerfile
	docker build -t prometheus-img . -f .\prometheus\Dockerfile 
	docker build -t grafana-img . -f .\grafana\Dockerfile

.PHONY: docker-clean
docker-clean:
	docker stop weather-service prometheus grafana
	docker rm weather-service prometheus grafana


.PHONY: docker-delete
docker-delete:
	docker rmi  weather-service-img prometheus-img grafana-img -f

.PHONY: up
up:
	docker-compose up

.PHONY : all
all : java-build docker-build up