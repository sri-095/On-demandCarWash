package com.capg.ocw.model.dto;

import lombok.Data;

@Data
public class PaymentDto{
	
	private String customerId;
	private String type;
	private String cardNo;
	private String pin;

}
