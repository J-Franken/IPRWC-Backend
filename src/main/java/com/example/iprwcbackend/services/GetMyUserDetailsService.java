package com.example.iprwcbackend.services;

import com.example.iprwcbackend.dao.AccountDao;
import com.example.iprwcbackend.model.Account;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class GetMyUserDetailsService implements UserDetailsService {
    private final AccountDao accountDao;

    public GetMyUserDetailsService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> accountRes = Optional.ofNullable(accountDao.getAccountByEmail(email));
        if (accountRes.isEmpty())
            throw new UsernameNotFoundException("Could not findUser with email = " + email);
        Account account = accountRes.get();
        return new org.springframework.security.core.userdetails.User(
                email,
                account.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
