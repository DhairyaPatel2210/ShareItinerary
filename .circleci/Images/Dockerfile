FROM --platform=$TARGETPLATFORM cimg/base:2024.01

LABEL maintainer="Your Name <your.email@example.com>"

ENV JAVA_VERSION 22
ENV JAVA_HOME /usr/local/jdk-${JAVA_VERSION}

RUN [[ $(uname -m) == "x86_64" ]] && ARCH="x64" || ARCH="aarch64" && \
    JAVA_MAJOR_VERSION=${JAVA_VERSION} && \
    JAVA_BUILD=$(curl -s "https://api.github.com/repos/adoptium/temurin${JAVA_MAJOR_VERSION}-binaries/releases" | jq "limit(1; .[] | select(.tag_name | startswith(\"jdk-${JAVA_VERSION}+\"))) | .tag_name | split(\"+\")[1]" | tr -d '"') && \
    URL="https://github.com/adoptium/temurin${JAVA_MAJOR_VERSION}-binaries/releases/download/jdk-${JAVA_VERSION}%2B${JAVA_BUILD%.*}/OpenJDK${JAVA_MAJOR_VERSION}U-jdk_${ARCH}_linux_hotspot_${JAVA_VERSION}_${JAVA_BUILD%.*}.tar.gz" && \
    curl -sSL -o java.tar.gz "${URL}" && \
    sudo mkdir /usr/local/jdk-${JAVA_VERSION} && \
    sudo tar -xzf java.tar.gz --strip-components=1 -C /usr/local/jdk-${JAVA_VERSION} && \
    rm java.tar.gz && \
    sudo ln -s /usr/local/jdk-${JAVA_VERSION}/bin/* /usr/bin/ && \
    sudo mkdir -p /etc/ssl/certs/java/cacerts && \
    sudo ln -s ${JAVA_HOME}/lib/security/cacerts /etc/ssl/certs/java/cacerts && \
    sudo apt-get update && sudo apt-get install -y \
        fontconfig \
    && \
    sudo rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* && \
    java --version && \
    javac --version

ENV MAVEN_VERSION=3.9.6 \
    PATH=/opt/apache-maven/bin:$PATH
RUN dl_URL="https://dlcdn.apache.org/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz" && \
    curl -sSL --fail --retry 3 $dl_URL -o apache-maven.tar.gz && \
    sudo tar -xzf apache-maven.tar.gz -C /opt/ && \
    rm apache-maven.tar.gz && \
    sudo ln -s /opt/apache-maven-* /opt/apache-maven && \
    mvn --version

ENV GRADLE_VERSION=8.5 \
    PATH=/opt/gradle/bin:$PATH
RUN dl_URL="https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" && \
    curl -sSL --fail --retry 3 $dl_URL -o gradle.zip && \
    sudo unzip -d /opt gradle.zip && \
    rm gradle.zip && \
    sudo ln -s /opt/gradle-* /opt/gradle && \
    gradle --version

ENV SBT_VERSION=1.9.8 \
    PATH=/opt/sbt/bin:$PATH
ENV MILL_VERSION=0.11.6
RUN dl_URL="https://github.com/sbt/sbt/releases/download/v${SBT_VERSION}/sbt-${SBT_VERSION}.tgz" && \
    curl -sSL --fail --retry 3 $dl_URL -o sbt.tar.gz && \
    sudo tar -xzf sbt.tar.gz -C /opt/ && \
    sudo ln -s /opt/sbt-* /opt/sbt && \
    sbt about && \
    rm -rf sbt.tar.gz ~/project/project ~/project/target && \
    mill_URL="https://github.com/com-lihaoyi/mill/releases/download/${MILL_VERSION}/${MILL_VERSION}" && \
    curl -sSL --fail --retry 3 $mill_URL > mill && \
    sudo mv mill /usr/local/bin/mill && chmod +x /usr/local/bin/mill