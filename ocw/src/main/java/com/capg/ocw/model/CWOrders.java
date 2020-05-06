package com.capg.ocw.model;

import java.sql.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "CWOrders")
@Getter
@Setter
public class CWOrders extends CWObject{
	
	private String orderId;
	private String customerId;
	private String type;
	private double cost;
	private char washerAssigned;
	private String washerId;
	private Date orderedDate;
}
