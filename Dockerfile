FROM openjdk:11-jdk
RUN apt-get update && apt-get -y install sudo
RUN apt-get -y install imagemagick
RUN apt-get -y install gifsicle
RUN apt-get -y install
VOLUME ["/var/log"]
ARG JAR_FILE="build/libs/today-earth-0.0.1-SNAPSHOT.jar"
COPY ${JAR_FILE} app.jar
ENV    PROFILE prod
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar","/app.jar"]