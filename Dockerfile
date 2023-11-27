FROM openjdk:17
COPY "./target/EL_ARTESANO-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8092
ENTRYPOINT [ "java", "-jar", "app.jar" ]