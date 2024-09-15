FROM --platform=$TARGETPLATFORM eclipse-temurin:22

ENV SPRING_PROFILES_ACTIVE=prod

# copying the build to working directory
COPY build/libs/*.jar app.jar

# running the final code
ENTRYPOINT ["java", "-jar", "app.jar"]