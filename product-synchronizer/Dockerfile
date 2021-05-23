FROM openjdk:8-jdk-alpine
WORKDIR /app
ADD target/product-sync.jar /app/product-sync.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/product-sync.jar"]
