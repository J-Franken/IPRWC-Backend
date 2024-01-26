package com.example.iprwcbackend.services;


import com.example.iprwcbackend.model.Account;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminCheckService {
    public boolean adminCheck(Optional<Account> account) {
        return account.map(Account::isAdmin).orElse(false);
    }
}
