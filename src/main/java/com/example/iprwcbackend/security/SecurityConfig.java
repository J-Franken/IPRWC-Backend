package com.example.iprwcbackend.security;

//import com.example.iprwcbackend.dao.AccountRepository;
//import com.example.iprwcbackend.services.MyUserDetailsService;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private AccountRepository accountRepository;
//    @Autowired
//    private JWTFilter filter;
//    @Autowired
//    private MyUserDetailsService uds;
//
//    @Bean
//    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .httpBasic().disable()
//                .cors()
//                .and()
//                .authorizeHttpRequests()
//                .antMatchers("/api/v1/auth/**").permitAll()
//                .antMatchers("/api/v1/user/**").hasRole("USER")
//                .antMatchers("/api/v1/chargingstation/**").hasRole("USER")
//                .antMatchers("/api/v1/employee/**").hasRole("USER")
//                .antMatchers("/api/v1/visit/**").hasRole("USER")
//                .antMatchers("/api/v1/reservation/**").hasRole("USER")
//                .antMatchers("/api/v1/location/**").hasRole("USER")
//                .and()
//                .userDetailsService(uds)
//                .exceptionHandling()
//                .authenticationEntryPoint(
//                        (request, response, authException) ->
//                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
//                )
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//}