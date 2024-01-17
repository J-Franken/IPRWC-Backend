package com.example.iprwcbackend.controller;

import com.example.iprwcbackend.dao.AccountDao;
import com.example.iprwcbackend.model.Account;
import com.example.iprwcbackend.model.ApiResponse;
import com.example.iprwcbackend.model.LoginCredentials;
import com.example.iprwcbackend.security.JWTUtil;
import com.example.iprwcbackend.services.InvalidMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "${frontend_url}")
public class AuthController {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final InvalidMailService invalidMailService;


    public AuthController(InvalidMailService invalidMailService) {
        this.invalidMailService = invalidMailService;
    }

    @PostMapping("/register")
    public Object registerHandler(@RequestBody Account account) {
        try {
            if (invalidMailService.patternMatches(account.getEmail())) {
                String encodedPass = passwordEncoder.encode(account.getPassword());
                account.setPassword(encodedPass);
                accountDao.addAccount(account);
                return new ApiResponse(HttpStatus.ACCEPTED, jwtUtil.generateToken(account.getEmail()));
            } else {
                return new ApiResponse(HttpStatus.BAD_REQUEST, "Invalid email");
            }
        } catch (Exception e) {
            return new ApiResponse(HttpStatus.BAD_REQUEST, "Email already in use");
        }
    }

    @PostMapping("/login")
    public Object loginHandler(@RequestBody LoginCredentials body) {
        try {
            if (invalidMailService.patternMatches(body.getEmail())) {
                UsernamePasswordAuthenticationToken authInputToken =
                        new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
                authManager.authenticate(authInputToken);
                return new ApiResponse(HttpStatus.ACCEPTED, jwtUtil.generateToken(body.getEmail()));
            } else {
                return new ApiResponse(HttpStatus.BAD_REQUEST, "Invalid email");
            }
        } catch (AuthenticationException authExc) {
            return new ApiResponse(HttpStatus.UNAUTHORIZED, "Invalid email/password");
        }
    }


    @GetMapping("/info")
    public ApiResponse getUserDetails() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ApiResponse(HttpStatus.ACCEPTED, accountDao.findByEmail(email));
    }
}
