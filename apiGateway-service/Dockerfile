FROM amazoncorretto:11

VOLUME /tmp

RUN yum install -y tzdata
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

# ENTRYPOINT ["java", "-Dspring.config.location=/secret/application.yml","-jar","/app.jar"]