FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD producer-0.0.1-SNAPSHOT.jar app.jar
RUN apk add --update curl && sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
EXPOSE 8000
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]