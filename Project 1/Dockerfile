FROM ubuntu:22.04

ENV DEBIAN_FRONTEND=noninteractive 

RUN apt-get update && \
    apt-get install -y software-properties-common && \
    apt install unzip -y && \
    apt install curl -y

RUN add-apt-repository ppa:deadsnakes/ppa

RUN apt install python3.8 -y && \
    apt install openjdk-8-jdk -y && \
    apt install kotlin -y

WORKDIR /app

RUN curl -L https://services.gradle.org/distributions/gradle-8.4-bin.zip -o gradle-8.4-bin.zip
RUN unzip gradle-8.4-bin.zip

ENV GRADLE_HOME=/app/gradle-8.4
ENV PATH=$PATH:$GRADLE_HOME/bin

RUN cd / && mkdir project && cd project && \
    gradle init --type kotlin-application && \
    sed -i "31i \ \ \ \ implementation(\"org.xerial:sqlite-jdbc:3.7.2\")" ./app/build.gradle.kts && \
    gradle build

CMD cd /project && gradle run
