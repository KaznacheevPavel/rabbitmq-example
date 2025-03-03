FROM bellsoft/liberica-openjdk-debian:21
WORKDIR /consumer
COPY target/*.jar consumer.jar
COPY src/main/resources/application.properties application.properties
ENTRYPOINT ["java", "-jar", "consumer.jar", "--spring.config.location=classpath:/application.properties"]