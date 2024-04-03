package com.example.iprwcbackend.dao;

import com.example.iprwcbackend.model.Promo;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Component
public class PromoDao {
    private final PromoRepository promoRepository;

    public PromoDao(PromoRepository promoRepository) {
        this.promoRepository = promoRepository;
    }

    public List<Promo> getAllPromo() {
        return promoRepository.findAll();
    }

    public Promo getPromoById(Integer id) {
        return promoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Promo not found with id " + id));
    }

    public Promo addPromo(Promo promo) {
        return promoRepository.save(promo);
    }

    public Promo updatePromo(Integer id, Promo updatedPromo) {
        Promo promo = getPromoById(id);
        promo.setCode(updatedPromo.getCode());
        promo.setDiscount_in_percentage(updatedPromo.getDiscount_in_percentage());
        return promoRepository.save(promo);
    }

    public void deletePromo(Integer id) {
        promoRepository.deleteById(id);
    }
}
