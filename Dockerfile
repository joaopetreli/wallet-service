FROM amazoncorretto:21-alpine3.18-jdk AS build

WORKDIR /build
RUN apk update && \
    apk add maven

ENV JAR_NAME=infrastructure*.jar

COPY . .
RUN [ "mvn", "-Dmaven.test.skip=true", "package" ]
RUN mv ./infrastructure/target/$JAR_NAME ./app.jar

FROM amazoncorretto:21-alpine3.18-jdk AS runner
RUN apk update && \
    apk --no-cache add curl

WORKDIR /app
COPY --from=build /build/app.jar ./app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]