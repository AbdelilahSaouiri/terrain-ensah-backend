FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/ensah-terrain-0.0.1-SNAPSHOT.jar /app/ensah-terrain-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ensah-terrain-0.0.1-SNAPSHOT.jar"]

