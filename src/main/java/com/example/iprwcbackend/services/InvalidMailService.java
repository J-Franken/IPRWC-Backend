package com.example.iprwcbackend.services;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class InvalidMailService {
    public boolean patternMatches(String mail) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern)
                .matcher(mail)
                .matches();
    }
}
