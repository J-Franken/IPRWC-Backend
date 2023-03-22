package com.example.iprwcbackend.controller;

import com.example.iprwcbackend.dao.ProductDao;
import com.example.iprwcbackend.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
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
    public Product addProduct(@RequestBody Product product) {
        return productDao.addProduct(product);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        return productDao.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteProduct(@PathVariable Integer id) {
        productDao.deleteProduct(id);
    }
}
