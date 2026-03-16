FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY demo /app

RUN apt-get update && apt-get install -y python3 python3-pip

RUN pip3 install numpy pandas scikit-learn

RUN chmod +x mvnw

RUN ./mvnw clean package

CMD ["java","-jar","target/demo-0.0.1-SNAPSHOT.jar"]
