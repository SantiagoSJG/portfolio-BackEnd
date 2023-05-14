FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY target/LogIn-0.0.1-SNAPSHOT.jar /LogIn-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/LogIn-0.0.1-SNAPSHOT.jar"]