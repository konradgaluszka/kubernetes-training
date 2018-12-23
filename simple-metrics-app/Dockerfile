FROM openjdk:8-jdk-alpine
MAINTAINER konrad.galuszka
COPY 'target/simple-metrics-app-1.0-SNAPSHOT.jar' '/simple-metrics-app/simple-metrics-app-1.0-SNAPSHOT.jar'
ADD 'target/lib' 'simple-metrics-app/lib'
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/simple-metrics-app/simple-metrics-app-1.0-SNAPSHOT.jar"]
EXPOSE 8080