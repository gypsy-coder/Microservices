FROM java:8
EXPOSE 8089
ADD target/service-two.jar service-two.jar
ENTRYPOINT ["java", "-jar", "/service-two.jar"]