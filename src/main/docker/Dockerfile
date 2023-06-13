FROM openjdk:17-jdk-alpine

RUN mkdir -p /usr/local/service

RUN echo $JAVA_HOME

ADD @project.build.finalName@.jar /usr/local/service/

COPY run.sh .

RUN chmod +x run.sh

ENTRYPOINT ["./run.sh"]