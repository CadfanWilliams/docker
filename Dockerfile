FROM gradle:8.10.1 AS base

WORKDIR /app
COPY . .

# Build the application

CMD ./gradlew run

FROM base AS prod
ENTRYPOINT ./gradlew run --console=plain

FROM base AS test
ENTRYPOINT ./gradlew test --console=plain