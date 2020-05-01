package com.capg.ocw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ocw.model.dto.PromoCodeDto;
import com.capg.ocw.operation.PromoCodeOperation;

@RestController
@RequestMapping("/api/promocode")
public class PromoCodeContoller {
	
	@Autowired
	PromoCodeOperation promoCodeOperation;
	
	@PostMapping("/addorupdatepromocode")
	public ResponseEntity<List<PromoCodeDto>> addOrUpdatePromoCode(@RequestBody List<PromoCodeDto> promoCodes) {
		return new ResponseEntity<>(promoCodeOperation.addOupdatePromoCode(promoCodes),HttpStatus.OK);
	}
	
	@GetMapping("/getpromocodes")
	public ResponseEntity<List<PromoCodeDto>> getAllPromoCodes(){
		return new ResponseEntity<>(promoCodeOperation.getAllPromoCodes(),HttpStatus.OK);
	}
}
