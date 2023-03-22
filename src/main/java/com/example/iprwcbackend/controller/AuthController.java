//package com.example.iprwcbackend.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@CrossOrigin("*")
//public class AuthController {
//
////    @Autowired
////    private AuthenticationManager authenticationManager;
////
////    @Autowired
////    private JwtTokenGenerator jwtTokenGenerator;
////
////    @PostMapping("/login")
////    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
////
////        Authentication authentication = authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(
////                        loginRequest.getUsername(),
////                        loginRequest.getPassword()
////                )
////        );
////
////        SecurityContextHolder.getContext().setAuthentication(authentication);
////
////        String jwtToken = jwtTokenGenerator.generateToken(authentication.getName());
////
////        return ResponseEntity.ok(new JwtAuthenticationResponse(jwtToken));
////    }
//}