FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=build/libs/'kot_i_kit-0.0.1-SNAPSHOT-plain.jar'
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]