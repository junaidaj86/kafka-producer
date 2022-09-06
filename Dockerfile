FROM openjdk:17
ADD target/URLFeeder.jar URLFeeder.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "URLFeeder.jar"]