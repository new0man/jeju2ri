FROM maven:3.6-jdk-11

## Mount or copy (see docker-compose.yaml)
COPY . /app
WORKDIR /app

# RUN mvn clean spring-boot:run
