package com.capg.ocw.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "Address")
public class Adderss extends CWObject{
	
	private String addressLine;
	private String zipCode;
	private String city;
	private String customerId;

}
