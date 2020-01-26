FROM openjdk:12
EXPOSE 8080
ADD target/market-place-price-docker.jar market-place-price-docker.jar
ENTRYPOINT ["java","-jar","/market-place-price-docker.jar"]