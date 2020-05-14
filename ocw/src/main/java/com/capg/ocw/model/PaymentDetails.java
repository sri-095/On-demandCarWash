package com.capg.ocw.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "PaymentDetails")
public class PaymentDetails extends CWObject{
	
	private String type;
	private String cardNo;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String pin;
	private String customerId;

}
