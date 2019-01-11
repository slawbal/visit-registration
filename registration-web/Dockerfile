FROM openjdk:8

COPY /target/registration-web-0.0.1-SNAPSHOT.jar /usr/src/myapp/registration-web.jar
WORKDIR /usr/src/myapp
RUN ls
CMD ["java", "-jar", "registration-web.jar"]
