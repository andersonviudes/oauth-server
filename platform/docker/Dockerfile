FROM openjdk:11.0.4-jre-slim

COPY main/build/libs/*-SNAPSHOT.jar /home/appuser/app.jar

ENTRYPOINT ["java","-XX:+UseContainerSupport","-XX:+UseStringDeduplication","-XX:G1ReservePercent=10", "-XX:+UseG1GC","-XX:ParallelGCThreads=3" ,"-XX:ConcGCThreads=3" ,"-XX:InitiatingHeapOccupancyPercent=60","-XX:MaxGCPauseMillis=100","-Djava.security.egd=file:/dev/./urandom" ,"-jar","/home/appuser/app.jar"]
