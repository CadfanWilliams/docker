FROM gradle:8.10.1 AS base

WORKDIR /app
COPY . .

# Build the application
RUN ./gradlew install

CMD ./gradlew run


FROM base AS test
CMD ./gradlew test

#FROM openjdk:17-alpine

#WORKDIR /app
#COPY --from=build /app/build/libs/*.jar app.jar

#EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]