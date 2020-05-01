package com.capg.ocw.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Document(collection = "PromoCode")
public class PromoCode extends CWObject {
	
	private String promoCode;

}
