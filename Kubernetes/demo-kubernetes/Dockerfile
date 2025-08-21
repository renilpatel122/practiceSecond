FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/demo-kubernetes-0.0.1-SNAPSHOT.jar demo-kubernetes.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","demo-kubernetes.jar"]
