FROM openjdk:8-jdk-alpine
WORKDIR /app
ADD target/product-info.jar /app/product-info.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/product-info.jar"]
