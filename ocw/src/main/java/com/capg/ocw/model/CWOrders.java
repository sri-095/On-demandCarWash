package com.capg.ocw.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "CWOrders")
@Getter
@Setter
public class CWOrders extends CWObject{
	
	private CarDetails carDetails;
	private Customer customer;
	private CWServicePlan servicePlan;
	private Washer washer;

}
