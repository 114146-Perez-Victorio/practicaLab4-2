FROM openjdk:17-jdk-alpine
COPY target/lciii-scaffolding-0.0.1-SNAPSHOT.jar gestion-app.jar
ENTRYPOINT ["java","-jar","gestion-app.jar"]