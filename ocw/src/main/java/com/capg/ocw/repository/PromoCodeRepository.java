package com.capg.ocw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.capg.ocw.model.PromoCode;

@Repository
public interface PromoCodeRepository extends MongoRepository<PromoCode, String> {
	
	PromoCode findByPromoCode(String promoCode);

}
