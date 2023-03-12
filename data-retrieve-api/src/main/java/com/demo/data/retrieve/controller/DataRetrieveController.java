package com.demo.data.retrieve.controller;

import com.demo.data.retrieve.factory.TransactionRepo;
import com.demo.data.retrieve.model.*;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.dao.QueryTimeoutException;

@RestController
@RequestMapping("/v1")
public class DataRetrieveController {
    private static final Logger logger = LoggerFactory.getLogger(DataRetrieveController.class);

    private final TransactionRepo transactionDao;

    public DataRetrieveController(TransactionRepo transactionDao) {
        this.transactionDao = transactionDao;
    }

    @GetMapping("/data-retrieve")
    @Operation(summary = "data-retrieve api")
    public ResponseEntity<Object> retrieve(@RequestParam("IBAN") String iban, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        if (iban == null) {
            return ResponseEntity.badRequest().body(new Response(new ErrorResponseBean(HttpStatus.BAD_REQUEST.value(), "Misformed request body")));
        }

        logger.info("IBAN=" + iban + ", page=" + page + ", size=" + size);
        Page<Transaction> pageResult;
        try {
            PageRequest pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
            pageResult = this.transactionDao.findTransactionsByIBAN(iban, pageable);
        } catch (QueryTimeoutException  ex) {
            logger.error("TimeoutException = " + ex);
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new Response(new ErrorResponseBean(HttpStatus.GATEWAY_TIMEOUT.value(), "Query timeout please try again")));
        } catch (Exception ex) {
            logger.error("Exception = " + ex);
            return ResponseEntity.internalServerError().body(new Response(new ErrorResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error")));
        }

        if (pageResult != null) {
            return ResponseEntity.ok(new Response(pageResult.getTotalPages(), pageResult.getNumber(), pageResult.getSize(), pageResult.getContent()));
        } else {
            logger.error("pageResult is null");
            return ResponseEntity.internalServerError().body(new Response(new ErrorResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error")));
        }
    }
}
