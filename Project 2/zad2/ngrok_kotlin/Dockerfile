FROM gradle:jdk8

RUN mkdir $HOME/ktor-app
WORKDIR $HOME/ktor-app

COPY zad2 .
RUN gradle build

CMD gradle run
