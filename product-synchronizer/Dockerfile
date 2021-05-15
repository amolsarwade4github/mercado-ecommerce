FROM openjdk:8-jdk-alpine
ADD target/product-info.jar /app/product-info.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/product-info.jar"]
