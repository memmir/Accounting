package com.springboot.accounting.service;

import com.springboot.accounting.model.Account;
import com.springboot.accounting.model.Transaction;
import com.springboot.accounting.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {
    private Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    protected Transaction initiateMoney(final Account account, BigDecimal amount){

        return transactionRepository.save(
                new Transaction(amount, account)
        );
    }
}
