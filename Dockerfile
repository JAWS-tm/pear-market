# Build stage
FROM maven:3.6.0-jdk-11-slim AS build
COPY app/src /home/app/src
COPY app/pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Package stage
FROM tomcat:9.0.62-jdk17-openjdk
COPY --from=build /home/app/target/pear-market.war /usr/local/tomcat/webapps/ROOT.war
# ENV CATALINA_OPTS="-Dkey=value"
EXPOSE 8080
CMD ["catalina.sh", "run"]