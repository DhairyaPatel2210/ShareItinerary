FROM --platform=$TARGETPLATFORM eclipse-temurin:22

# copying the build to working directory
COPY build/libs/*.jar app.jar

# running the final code
ENTRYPOINT ["java", "-jar", "app.jar"]