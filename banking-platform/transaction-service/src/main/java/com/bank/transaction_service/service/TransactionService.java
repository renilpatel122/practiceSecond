package com.bank.transaction.service;

import com.bank.transaction.entity.Transaction;
import com.bank.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public Transaction createTransaction(Long accountId, Double amount, String type) {
        Transaction tx = new Transaction();
        tx.setAccountId(accountId);
        tx.setAmount(amount);
        tx.setType(type);
        tx.setTimestamp(LocalDateTime.now());
        return repository.save(tx);
    }
}
