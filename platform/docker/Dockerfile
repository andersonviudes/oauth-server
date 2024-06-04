FROM eclipse-temurin:17-jre

COPY main/build/libs/app.jar /home/appuser/app.jar

COPY platform/newrelic /home/appuser/newrelic

ENTRYPOINT ["java", "-javaagent:/home/appuser/newrelic/newrelic.jar", "-XX:+UseContainerSupport","-XX:+UseStringDeduplication","-XX:G1ReservePercent=10", "-XX:+UseG1GC","-XX:ParallelGCThreads=3" ,"-XX:ConcGCThreads=3" ,"-XX:InitiatingHeapOccupancyPercent=60","-XX:MaxGCPauseMillis=100","-Djava.security.egd=file:/dev/./urandom", "-Dnewrelic.config.file=/home/appuser/newrelic/newrelic.yml" ,"-jar","/home/appuser/app.jar"]
