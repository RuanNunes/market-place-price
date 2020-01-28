.DEFAULT_GOAL := help

help:  ## Exibir essa ajuda
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m<target>\033[0m\n"} /^[a-zA-Z_-]+:.*?##/ { printf "  \033[36m%-15s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)

##@ Market Place Price

build: ## Gera o arquivo .jar
	docker run --rm \
		-v "$(shell pwd)":/app \
		-w /app \
	maven:3.6.3-jdk-13 \
	mvn clean install

run: ## Cria o container que executa o Market Place Price
	docker run --name marketapi-srv -d --restart=unless-stopped \
		-e VIRTUAL_HOST=market.api.jeudi.dev \
		-e VIRTUAL_PORT=8080 \
		-e LETSENCRYPT_HOST=market.api.jeudi.dev \
		-p 8080:8080 \
		-v "$(shell pwd)":/app \
	openjdk:13 \
	java -jar /app/target/market-place-price-docker.jar

start: ## Inicia o container market place price
	docker start marketapi-srv

stop: ## Para o container market place price
	docker stop marketapi-srv

_rm: ## Remove o coantiner market place price
	docker stop marketapi-srv
	docker rm marketapi-srv

log: ## Log de execucao do continer market place price
	docker logs --tail 50 --follow marketapi-srv

restart: ## Reiniciar o container  market place price
	docker restart marketapi-srv
