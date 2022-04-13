FROM ubuntu:20.04

# Install OpenJDK-11

RUN apt update && apt upgrade -y

RUN apt-cache search openjdk

RUN apt-get install openjdk-17-jdk -y

RUN apt-get clean

EXPOSE 8080

COPY /weather/build/libs/weather-0.0.1.jar weather-0.0.1.jar

CMD java -jar weather-0.0.1.jar