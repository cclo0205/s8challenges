# Data-Sink-MongoDB

## Start service
```
mvn spring-boot:run
```

## Health check
```
curl http://localhost:8080/actuator/health
curl http://localhost:8080/actuator/health/liveness
curl http://localhost:8080/actuator/health/readiness
```