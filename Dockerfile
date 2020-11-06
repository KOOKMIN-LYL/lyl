FROM java:8
LABEL maintainer="dlwlsrn94@naver.com"
EXPOSE 8089
ARG JAR_FILE=./target/lyl-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} lyl.jar
ENTRYPOINT ["java", "-jar", "/lyl.jar"]