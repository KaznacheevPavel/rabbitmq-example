FROM bellsoft/liberica-openjdk-debian:21
WORKDIR /producer
COPY target/*.jar producer.jar
COPY src/main/resources/application.properties application.properties
ENTRYPOINT ["java", "-jar", "producer.jar", "--spring.config.location=classpath:/application.properties"]