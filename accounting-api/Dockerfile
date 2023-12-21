#FROM openjdk:17 AS build

#COPY pom.xml mvnw ./
#COPY .mvn .mvn
#RUN ./mvnw dependency:resolve

#COPY src src
#RUN ./mvnw package

FROM openjdk:17
WORKDIR Accounting-api
COPY target/Accounting-0.0.1-SNAPSHOT.jar Accounting-api.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "Accounting-api.jar"]

#Docker container çalıştırma
# docker run --name account -d -p 9090:8080 accounting:1.0

#Docker container oluşturma
#docker build . -t accounting:1.0

#Docker çalışan container ları gösterme
#docker ps -a

#Docker çalışan container ı kaldırma
#docker  container rm -f {id}

#Docker image larını gösterme
#docker images