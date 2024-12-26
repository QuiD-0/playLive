FROM openjdk:21

ENV APP_JAR=playLive.jar
ENV OPTIONS=""

COPY build/libs/playLive-0.0.1.jar $APP_JAR

EXPOSE 8888
ENTRYPOINT ["sh", "-c", "java $OPTIONS -jar $APP_JAR"]
