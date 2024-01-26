package com.example.iprwcbackend.controller;

import com.example.iprwcbackend.dao.AccountDao;
import com.example.iprwcbackend.dao.ProductDao;
import com.example.iprwcbackend.model.Account;
import com.example.iprwcbackend.model.ApiResponse;
import com.example.iprwcbackend.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/products")
@CrossOrigin(origins = "${frontend_url}")
public class ProductController {

    private final ProductDao productDao;
    private final AccountDao accountDao;

    public ProductController(ProductDao productDao, AccountDao accountDao) {
        this.productDao = productDao;
        this.accountDao = accountDao;
    }

    @GetMapping
    @ResponseBody
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable Integer id) {
        return productDao.getProductById(id);
    }

    @PostMapping
    @ResponseBody
    public Object addProduct(@RequestBody Product product) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountDao.findByEmail(email).get();
        if(!this.accountDao.isAccountAdmin(account)){
            return new ApiResponse<>(HttpStatus.UNAUTHORIZED, "You are not authorized to use this.");
        }
        return productDao.addProduct(product);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Object updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountDao.findByEmail(email).get();
        if(!this.accountDao.isAccountAdmin(account)){
            return new ApiResponse<>(HttpStatus.UNAUTHORIZED, "You are not authorized to use this.");
        }
        return productDao.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Object deleteProduct(@PathVariable Integer id) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountDao.findByEmail(email).get();
        if(!this.accountDao.isAccountAdmin(account)){
            return new ApiResponse<>(HttpStatus.UNAUTHORIZED, "You are not authorized to use this.");
        }
        productDao.deleteProduct(id);
        return new ApiResponse<>(HttpStatus.ACCEPTED, "You deleted some data!");
    }
}
