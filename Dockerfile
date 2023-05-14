FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY C:/Users/Santi/Documents/NetBeansProjects/LogIn/target/LogIn-0.0.1-SNAPSHOT.jar /app.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]