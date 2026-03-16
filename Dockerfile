FROM openjdk:17

WORKDIR /app

COPY . .

WORKDIR /app/demo

RUN chmod +x mvnw

RUN ./mvnw clean package

CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
