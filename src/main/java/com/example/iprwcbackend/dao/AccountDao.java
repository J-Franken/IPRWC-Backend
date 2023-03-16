package com.example.iprwcbackend.dao;

public class AccountDao {
    private final AccountRepository accountRepository;

    public AccountDao(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
