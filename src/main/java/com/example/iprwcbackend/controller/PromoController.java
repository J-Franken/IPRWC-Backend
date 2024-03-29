package com.example.iprwcbackend.controller;

import com.example.iprwcbackend.dao.PromoDao;
import com.example.iprwcbackend.model.Promo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/api/coupons")
@CrossOrigin(origins = "${frontend_url}")
public class PromoController {
}