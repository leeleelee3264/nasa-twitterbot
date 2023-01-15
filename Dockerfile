FROM openjdk:11-jdk
RUN apt-get update && apt-get -y install sudo
RUN apt-get -y install imagemagick
RUN apt-get -y install gifsicle
RUN apt-get -y install
ARG JAR_FILE="build/libs/nasa-bot-0.0.1-SNAPSHOT.jar"
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=local", "-jar","/app.jar"]