# Data-Retrieve-API

## Start service
```
mvn spring-boot:run
```

## Try API
```
curl --request GET 'http://localhost:8080/v1/data-retrieve?IBAN=CH93-0000-0000-0000-0000-0&page=0&size=5'
```

## Health check
```
curl http://localhost:8080/actuator/health
curl http://localhost:8080/actuator/health/liveness
curl http://localhost:8080/actuator/health/readiness
```

## Swagger
```
http://localhost:8080/swagger-ui/index.html
```