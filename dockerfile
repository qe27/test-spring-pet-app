FROM java:8-jdk-alpine
COPY ./target/test-project-artifact-id-1.0-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "test-project-artifact-id-1.0-SNAPSHOT.jar"]