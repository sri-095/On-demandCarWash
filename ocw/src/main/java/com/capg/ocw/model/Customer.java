package com.capg.ocw.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection ="Customer")
@Getter
@Setter
public class Customer extends CWObject{
	
	private String customerId;
	private CarDetails carDetails;
	private String email;
	private long phoneNumber;
}
