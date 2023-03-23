package com.example.iprwcbackend.dao;

import com.example.iprwcbackend.model.Account;
import com.example.iprwcbackend.model.Product;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountDao {
    private final AccountRepository accountRepository;

    public AccountDao(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Integer id) {
        return accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account not found with id " + id));
    }

    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Account not found with email " + email));
    }

    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    public Account updateAccount(Integer id, Account updatedAccount) {
        Account account = getAccountById(id);
        account.setEmail(updatedAccount.getEmail());
        account.setPassword(updatedAccount.getPassword());
        account.setAdmin(updatedAccount.isAdmin());
        return accountRepository.save(account);
    }

    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);
    }
}
