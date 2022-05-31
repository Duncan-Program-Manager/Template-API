FROM openjdk:11 as rabbitmq
EXPOSE 8084 3306
ADD target/Template-API-0.0.1-SNAPSHOT.jar templateapi-docker.jar
ENTRYPOINT ["java","-jar","templateapi-docker.jar"]