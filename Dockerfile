FROM openjdk:17-oracle
ARG JAR_FILE=build/libs/paperdx-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=prod", "-Duser.timezone=Asia/Seoul", "-jar","/app.jar"]
