FROM openjdk:21 as message-routing-backend
COPY target/message-routing-backend.jar message-routing-backend.jar
EXPOSE 8080
CMD ["java", "-jar", "message-routing-backend.jar"]