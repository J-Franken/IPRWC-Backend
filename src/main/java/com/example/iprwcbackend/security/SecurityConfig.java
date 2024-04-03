package com.example.iprwcbackend.security;

import com.example.iprwcbackend.dao.AccountRepository;
import com.example.iprwcbackend.services.GetMyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JWTFilter filter;
    @Autowired
    private GetMyUserDetailsService uds;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic().disable()
                .cors()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/accounts").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/accounts/{id}").hasRole("USER")
                .antMatchers(HttpMethod.POST, "api/accounts/{id}").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/accounts/{id}").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/api/accounts/{id}").hasRole("USER")
                .antMatchers(HttpMethod.POST, "api/promocode/{id}").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/promocode/{id}").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/api/promocode/{id}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/promocode").permitAll()
                .antMatchers(HttpMethod.GET, "/api/promocode/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/products").permitAll()
                .antMatchers(HttpMethod.GET, "/api/products/{id}").permitAll()
                .antMatchers(HttpMethod.POST, "/api/orders").permitAll()
                .antMatchers(HttpMethod.POST, "/api/products").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api/products/{id}").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/products/{id}").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/api/products/{id}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/orders").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/orders/{id}").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/orders/{id}").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/api/orders/{id}").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/api/admin/**").hasRole("USER")
                .and()
                .userDetailsService(uds)
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                )
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}