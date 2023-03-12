package com.demo.data.collector.kafka;

import com.demo.data.collector.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class JsonKafkaProducer {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Value("${spring.kafka.producer.timeout}")
    private Long producerTimeout;

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

    private final KafkaTemplate<String, Request> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, Request> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Request req) throws ExecutionException, InterruptedException, TimeoutException {

        LOGGER.info(String.format("Message sent -> %s", req.toString()));

        Message<Request> message = MessageBuilder
                .withPayload(req)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        ListenableFuture<SendResult<String, Request>> future = kafkaTemplate.send(message);
        future.get(producerTimeout, TimeUnit.MILLISECONDS);
    }
}
