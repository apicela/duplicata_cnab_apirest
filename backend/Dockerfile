FROM gradle:8.3-jdk17

COPY . /app

WORKDIR /app

RUN gradle build

CMD ["java", "-jar", "build/libs/duplicata-0.0.1-SNAPSHOT.jar"]
