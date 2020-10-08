FROM openjdk:15
RUN mkdir /app
WORKDIR app
COPY mvnw .
RUN ["chmod", "+x", "mvnw"]
COPY ./pom.xml ./pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY ./src ./src
RUN ./mvnw clean package
ENTRYPOINT ["java","-jar","target/ProjectSpringBoot-0.0.1-SNAPSHOT.jar"]
