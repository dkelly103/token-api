# 1. Gradle docker image with Java 11
FROM gradle:6.9.2-jdk11 as jar-builder

# 2. Copy over gradle stuff and download the gradle distro
COPY settings.gradle.kts gradle.* gradlew /src/
COPY gradle /src/gradle
WORKDIR /src
RUN ./gradlew --version

# 3. Copy over the src and build the JAR
COPY build.gradle.kts package*.json /src/
COPY src src
#RUN ./gradlew generateContractWrapper build --stacktrace --no-daemon

## 4. From lightweight docker image
#FROM openjdk:11.0.13-jdk-slim
#
## 5. Copy JAR from previous image
#COPY --from=jar-builder /src/build/libs/*.jar /opt/app/app.jar
#
#CMD ["java","-jar","/opt/app/app.jar"]