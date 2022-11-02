FROM openjdk:11
VOLUME /tmp
EXPOSE 8081
ADD ./target/ProductService-0.0.1-SNAPSHOT.jar ms-product.jar
ENTRYPOINT ["java","-jar","/ms-product.jar"]