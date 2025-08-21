package com.bank.account.service;

import com.bank.account.entity.Account;
import com.bank.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public Account create(Account account) {
        return repository.save(account);
    }

    public Optional<Account> getByAccountNumber(String accountNumber) {
        return repository.findByAccountNumber(accountNumber);
    }

    public void updateBalance(String accountNumber, Double newBalance) {
        repository.findByAccountNumber(accountNumber).ifPresent(account -> {
            account.setBalance(newBalance);
            repository.save(account);
        });
    }
}
