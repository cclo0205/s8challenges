package com.demo.data.sink.mongodb.factory;


import com.demo.data.sink.mongodb.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends MongoRepository<Transaction, Long> {
    Optional<Transaction> findTransactionByuId(String uId);
    Optional<List<Transaction>> findTransactionsByIBAN(String iban);
}
