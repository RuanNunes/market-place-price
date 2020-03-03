FROM maven:3.6.3-jdk-13 AS packager

RUN mkdir -p /market-place-price

WORKDIR /market-place-price

ADD ./pom.xml .
ADD ./docker-entrypoint.sh /

RUN mvn clean install -X -f ./pom.xml

COPY ./ .


RUN mvn package -Dmaven.test.skip=true && \
    mv ./target/*.jar /run/market-place-price-docker.jar

FROM openjdk:13

COPY --from=packager /run/market-place-price-docker.jar /var/market-place-price/market-place-price-docker.jar
COPY --from=packager /docker-entrypoint.sh /docker-entrypoint.sh

RUN chmod +x /docker-entrypoint.sh

EXPOSE 8080

ENTRYPOINT [ "bash", "/docker-entrypoint.sh" ]
