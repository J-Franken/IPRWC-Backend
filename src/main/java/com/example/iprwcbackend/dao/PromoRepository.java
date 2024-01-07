package com.example.iprwcbackend.dao;

import com.example.iprwcbackend.model.Promo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromoRepository extends JpaRepository<Promo, Integer> {
    Optional<Promo> findByCode(String code);
}
