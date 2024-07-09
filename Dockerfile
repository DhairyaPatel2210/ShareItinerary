# importing the required JDK for java version 22
FROM --platform=$TARGETPLATFORM eclipse-temurin:22 AS build

ENV GRADLE_OPTS='-Dorg.gradle.jvmargs="-XX:MaxMetaspaceSize=1g"'

# copying needed code from the current folder to the image
COPY ./build.gradle /project/
COPY ./gradlew /project/
COPY ./gradlew.bat /project/
COPY ./settings.gradle /project/
COPY ./gradle /project/gradle/
COPY ./src /project/src/

# changing the working directory
WORKDIR /project

# executing the gradle build
RUN ./gradlew clean build -x test --no-daemon

# starting a new stage
FROM --platform=$TARGETPLATFORM eclipse-temurin:22

# copying the build to working directory
COPY --from=build /project/build/libs/*.jar app.jar

# running the final code
ENTRYPOINT ["java", "-jar", "app.jar"]