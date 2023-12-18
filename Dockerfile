FROM openjdk:17 AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17
WORKDIR Accounting-api
COPY --from=build target/*.jar Accounting-api.jar
ENTRYPOINT ["java", "-jar", "Accounting-api.jar"]

#Docker container oluşturma
# docker run --name account -d -p 9090:8080 accounting:1.0