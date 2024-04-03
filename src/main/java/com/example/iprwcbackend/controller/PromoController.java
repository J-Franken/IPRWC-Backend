package com.example.iprwcbackend.controller;

import com.example.iprwcbackend.dao.AccountDao;
import com.example.iprwcbackend.dao.PromoDao;
import com.example.iprwcbackend.model.Account;
import com.example.iprwcbackend.model.ApiResponse;
import com.example.iprwcbackend.model.Product;
import com.example.iprwcbackend.model.Promo;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/promocode")
@CrossOrigin(origins = "${frontend_url}")
public class PromoController {
    private final PromoDao promoDao;
    private final AccountDao accountDao;

    public PromoController(PromoDao promoDao, AccountDao accountDao) {
        this.promoDao = promoDao;
        this.accountDao = accountDao;
    }

    @GetMapping
    @ResponseBody
    public List<Promo> getAllPromo() {
        return promoDao.getAllPromo();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Promo getPromoById(@PathVariable Integer id) {
        return promoDao.getPromoById(id);
    }

    @PostMapping
    @ResponseBody
    public ApiResponse<Promo> addPromo(@RequestBody Promo promo) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountDao.findByEmail(email).get();
        if (!this.accountDao.isAccountAdmin(account)) {
            return new ApiResponse<>(HttpStatus.UNAUTHORIZED, "You are not authorized to use this.");
        }
        promoDao.addPromo(promo);
        return new ApiResponse<>(HttpStatus.ACCEPTED, "Succesfully added the promocode");
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ApiResponse<Promo> updatePromo(@PathVariable Integer id, @RequestBody Promo promo) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountDao.findByEmail(email).get();
        if (!this.accountDao.isAccountAdmin(account)) {
            return new ApiResponse<>(HttpStatus.UNAUTHORIZED, "You are not authorized to use this.");
        }
        promoDao.updatePromo(id, promo);
        return new ApiResponse<>(HttpStatus.ACCEPTED, "Succesfully updated the promocode");
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ApiResponse<Promo> deletePromo(@PathVariable Integer id) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountDao.findByEmail(email).get();
        if(!this.accountDao.isAccountAdmin(account)){
            return new ApiResponse<>(HttpStatus.UNAUTHORIZED, "You are not authorized to use this.");
        }
        promoDao.deletePromo(id);
        return new ApiResponse<>(HttpStatus.ACCEPTED, "You deleted the promocode");
    }
}