FROM openjdk:22-oracle 

WORKDIR /app

COPY /target/api-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "synopsar-api.jar"]