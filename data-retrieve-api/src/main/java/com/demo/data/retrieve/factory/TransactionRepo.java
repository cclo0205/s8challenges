package com.demo.data.retrieve.factory;


import com.demo.data.retrieve.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepo extends MongoRepository<Transaction, Long> {
    Page<Transaction> findTransactionsByIBAN(String iban, Pageable pageable);
}
