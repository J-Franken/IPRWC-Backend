package com.example.iprwcbackend.controller;

import com.example.iprwcbackend.dao.AccountDao;
import com.example.iprwcbackend.model.Account;
import com.example.iprwcbackend.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "${frontend_url}")
public class AccountController {

    private final AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @GetMapping
    @ResponseBody
    public List<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Account getAccountById(@PathVariable Integer id) {
        return accountDao.getAccountById(id);
    }

    @PostMapping
    @ResponseBody
    public ApiResponse addAccount(@RequestBody Account account) {
        accountDao.addAccount(account);
        return new ApiResponse(HttpStatus.ACCEPTED, "Account added successfully");
    }


    @PutMapping("/{id}")
    @ResponseBody
    public Account updateAccount(@PathVariable Integer id, @RequestBody Account account) {
        return accountDao.updateAccount(id, account);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteAccount(@PathVariable Integer id) {
        accountDao.deleteAccount(id);
    }
}
