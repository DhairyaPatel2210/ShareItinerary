# # importing the required JDK for java version 22
# FROM --platform=$TARGETPLATFORM eclipse-temurin:22 AS build

# # copying needed code from the current folder to the image
# COPY ./build.gradle /project/
# COPY ./gradlew /project/
# COPY ./gradlew.bat /project/
# COPY ./settings.gradle /project/
# COPY ./gradle /project/gradle/
# COPY ./src /project/src/

# # changing the working directory
# WORKDIR /project

# # executing the gradle build
# RUN ./gradlew clean build -x test 

FROM eclipse-temurin:22

# copying the build to working directory
# COPY ~/project/build/libs/*.jar app.jar

COPY ~/artifacts/*.jar app.jar

# running the final code
ENTRYPOINT ["java", "-jar", "app.jar"]