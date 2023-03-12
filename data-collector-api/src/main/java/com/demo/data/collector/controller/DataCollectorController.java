package com.demo.data.collector.controller;

import javax.validation.Valid;

import com.demo.data.collector.model.Request;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.data.collector.kafka.JsonKafkaProducer;
import com.demo.data.collector.model.Response;

import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/v1")
public class DataCollectorController {

    private final JsonKafkaProducer kafkaProducer;

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

    public DataCollectorController(JsonKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/data-collector")
    @Operation(summary = "data-collector api")
    public ResponseEntity<Response> collector(@Valid @RequestBody Request req) {

        LOGGER.info("request = " + req);
        if (req.getUId() == null || req.getMoney() == null || req.getIBAN() == null || req.getDate() == null || req.getDescription() == null) {
            return ResponseEntity.badRequest().body(new Response( 400, "Misformed request body"));
        }

        try {
            kafkaProducer.sendMessage(req);
        } catch (TimeoutException ex) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new Response(504, "Query timeout please try again"));
        } catch (Exception ex) {
            LOGGER.error("sendMessage error = " + ex);
            return ResponseEntity.internalServerError().body(new Response(500, "Internal server error"));
        }

        return ResponseEntity.ok(new Response(201, "Resource created"));
    }
}
