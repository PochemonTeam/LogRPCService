# Stage 1: Build and install the DTO project into local maven repository
FROM maven:3.8.3-openjdk-17 AS builder
WORKDIR /RPCLib

# Copy your DTO project
COPY ./RPCLib/pom.xml ./pom.xml
COPY ./RPCLib/src ./src

# Build the DTO project and install it into the local maven repository
RUN mvn clean install

WORKDIR /LogRPCService

# Copy your main project
COPY ./LogRPCService/pom.xml ./pom.xml
COPY ./LogRPCService/src ./src

# Build the main project with the dependencies
RUN mvn clean package -DskipTests

# Stage 3: Copy the artifact and run
FROM openjdk:17-jdk-slim
WORKDIR /LogRPCService

COPY --from=builder /LogRPCService/target/log-0.0.1-SNAPSHOT.jar ./app.jar

ENTRYPOINT ["java","-jar","app.jar"]