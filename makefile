.DEFAULT_GOAL := help

help:  ## Exibir essa ajuda
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m<target>\033[0m\n"} /^[a-zA-Z_-]+:.*?##/ { printf "  \033[36m%-15s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)

##@ Market Place Price

_clone: ## clona o projeto market place price
	git clone https://github.com/RuanNunes/market-place-price.git market-src

build: ## gerar arquivo .jar
	docker run --rm \
		-v "$(shell pwd)":/app \
		-w /app \
	maven:3.6.3-jdk-13 \
	mvn clean install

run: ## iniciar o container com a imagem Market Place Price
	docker run --name marketapi-srv -d --restart=unless-stopped \
		-e VIRTUAL_HOST=market.api.jeudi.dev \
		-e VIRTUAL_PORT=8080 \
		-e LETSENCRYPT_HOST=market.api.jeudi.dev \
		-p 8080:8080 \
		-v "$(shell pwd)":/app \
	openjdk:13 \
	java -jar /app/target/market-place-price-docker.jar

_stop: ## para o container do market place price
	docker stop marketapi-srv

_rm: ## remove o coantiner do market place price
	docker stop marketapi-srv
	docker rm marketapi-srv

log: ## exibe o log de execucao do caontiner market place price
	docker logs --tail 50 --follow marketapi-srv

_restart: ## reiniciar o container do market place price
	docker restart marketapi-srv

test: ## teste pwd
	echo $(shell pwd)
