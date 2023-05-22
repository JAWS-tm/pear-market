# Build stage
FROM maven:3.9.2-eclipse-temurin-11-alpine AS build
COPY app/src /home/app/src
COPY app/pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -e

# Package stage
FROM tomcat:9-jre11-temurin-focal
COPY --from=build /home/app/target/pear-market.war /usr/local/tomcat/webapps/ROOT.war
# ENV CATALINA_OPTS="-Dkey=value"
EXPOSE 8080
CMD ["catalina.sh", "run"]