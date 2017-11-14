FROM java:8
ADD microservice_hadoop-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"] 