package com.example.iprwcbackend.dao;

import com.example.iprwcbackend.model.Promo;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class PromoDao {
    private final PromoRepository promoRepository;

    public PromoDao(PromoRepository promoRepository) {
        this.promoRepository = promoRepository;
    }

    public Optional<Promo> getCouponByCode(String code) {
        return promoRepository.findByCode(code);
    }
}
