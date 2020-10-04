FROM java:8
EXPOSE 8088
ADD target/service-one.jar service-one.jar
ENTRYPOINT ["java", "-jar", "/service-one.jar"]