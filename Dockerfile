FROM gradle:7.5.1

WORKDIR /app
COPY . .

# Build the application
RUN ./gradlew install

CMD ./gradlew run
#FROM openjdk:17-alpine

#WORKDIR /app
#COPY --from=build /app/build/libs/*.jar app.jar

#EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]