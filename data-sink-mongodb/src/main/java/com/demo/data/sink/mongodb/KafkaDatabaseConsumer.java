package com.demo.data.sink.mongodb;

import com.demo.data.sink.mongodb.factory.TransactionRepo;
import com.demo.data.sink.mongodb.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);


    private ObjectMapper objectMapper = new ObjectMapper();
    private final TransactionRepo transactionDao;

    public KafkaDatabaseConsumer(TransactionRepo transactionDao) {
        LOGGER.info("Mongodb Consumer Start");
        this.transactionDao = transactionDao;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String eventMessage) {
        LOGGER.info(String.format("Event message received -> %s", eventMessage));
        try {
            Transaction transaction = objectMapper.readValue(eventMessage, Transaction.class);
            transaction.genTimestamp();
            //avoid duplicate transaction
            this.transactionDao.findTransactionByuId(transaction.getUId())
                    .ifPresentOrElse(t -> LOGGER.info("already exists =>" + t.getUId()), () -> {
                        this.transactionDao.insert(transaction);
                        LOGGER.info("add transaction unique id=" + transaction.getUId());
                    });
        } catch (Exception e) {
            // TODO: send error logs to log topic
            LOGGER.error(e.getMessage());
        }
    }
}
