package com.capg.ocw.operation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.capg.ocw.exception.OcwException;
import com.capg.ocw.model.PromoCode;
import com.capg.ocw.model.dto.PromoCodeDto;
import com.capg.ocw.repository.PromoCodeRepository;

@Component
public class PromoCodeOperation {
	
	@Autowired
	PromoCodeRepository promoCodeRepository;

	@Transactional(rollbackFor = OcwException.class)
	public List<PromoCodeDto> addOupdatePromoCode(List<PromoCodeDto> promoCodes) {
		List<PromoCode> promoCodeList = new ArrayList<>();
		for(PromoCodeDto promoCode : promoCodes) {
			PromoCode promoCodeDb = promoCodeRepository.findByPromoCode(promoCode.getPromoCode());
			if(null == promoCodeDb) 
				promoCodeDb = new PromoCode();

			promoCodeDb.setPromoCode(promoCode.getPromoCode());
			promoCodeDb.setName(promoCode.getName());
			promoCodeList.add(promoCodeDb);
		}
		promoCodeRepository.saveAll(promoCodeList);
		return getAllPromoCodes();
	}

	public List<PromoCodeDto> getAllPromoCodes() {
		List<PromoCodeDto> promoCodeDtos = new ArrayList<>();
		List<PromoCode> promoCodeDbList = promoCodeRepository.findAll();
		for(PromoCode promoCode : promoCodeDbList) {
			PromoCodeDto promoCodeNew = new PromoCodeDto();
			promoCodeNew.setPromoCode(promoCode.getPromoCode());
			promoCodeNew.setName(promoCode.getName());
			promoCodeDtos.add(promoCodeNew);
		}
		return promoCodeDtos;
	}

}
