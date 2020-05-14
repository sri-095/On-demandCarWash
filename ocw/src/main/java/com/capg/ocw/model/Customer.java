package com.capg.ocw.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Document(collection ="Customer")
@Getter
@Setter
public class Customer extends CWObject{
	
	private String customerId;
	private List<CarDetails> carDetails;
	private Adderss address;
	private long phoneNumber;
	private User customerUser;
	@DateTimeFormat(iso = ISO.DATE)
	private Date registeredDate;
	@DateTimeFormat(iso = ISO.DATE)
	private Date loggedIn;
	private PaymentDetails details;
	
}
