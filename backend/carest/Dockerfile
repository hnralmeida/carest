FROM eclipse-temurin:22-jdk AS build

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

RUN ls -l /app && cat /app/pom.xml
RUN chmod +x mvnw
RUN ./mvnw package -DskipTests

RUN ./mvnw package -DskipTests

FROM eclipse-temurin:22-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]