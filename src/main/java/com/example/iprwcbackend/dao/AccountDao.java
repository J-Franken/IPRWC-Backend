package com.example.iprwcbackend.dao;

import com.example.iprwcbackend.model.Account;
import com.example.iprwcbackend.services.CheckIfUserIsAdminService;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AccountDao {
    private final AccountRepository accountRepository;
    private final CheckIfUserIsAdminService checkIfUserIsAdminService;

    public AccountDao(AccountRepository accountRepository, CheckIfUserIsAdminService checkIfUserIsAdminService) {
        this.accountRepository = accountRepository;
        this.checkIfUserIsAdminService = checkIfUserIsAdminService;
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

    public Optional<Account> findByEmail(String email) {
        ArrayList<Account> accounts = (ArrayList<Account>) accountRepository.findAll();

        for (Account account : accounts) {
            if (account.getEmail().contains(email)) {

                return Optional.ofNullable(account);
            }
        }
        return Optional.empty();
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

    public boolean isAccountAdmin(Account account){
        return checkIfUserIsAdminService.adminCheck(accountRepository.findById(account.getId()));
    }
}

