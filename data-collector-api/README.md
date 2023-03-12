# Data-Collector-API

## Start service
```
mvn spring-boot:run
```

## Try API
```
curl --request POST 'http://localhost:8080/v1/data-collector' \
--header 'Content-Type: application/json' \
--data-raw '{
    "uId": "89d3o179-abcd-465b-o9ee-e2d5f6ofEld46",
    "IBAN": "CH93-0000-0000-0000-0000-0",
    "date": "11-08-2022",
    "description": "Online payment CHF",
    "money": {
      "amount": 1220,
      "currency": "CHF"
    }
}'
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
