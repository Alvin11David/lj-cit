FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /src
# Copy only the build files first, so dependencies are cached
COPY .mvn .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw -B -q dependency:go-offline

COPY src src

RUN ./mvnw -B -q -DskipTests package && cp target/*.jar app.jar

# ---- Stage 2: run ----
FROM eclipse-temurin:25-jre-alpine
RUN addgroup -S -g 10001 app && adduser -S -u 10001 -G app app
WORKDIR /app
COPY --from=build /src/app.jar app.jar
USER 10001:10001
EXPOSE 8080
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75 -XX:+ExitOnOutOfMemoryError"
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

