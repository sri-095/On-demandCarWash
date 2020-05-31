package com.capg.ocw.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "Washer")
@Getter
@Setter
public class Washer extends CWObject {
	
	private String washerId;
	private String notes;
	private float ratings;
	private List<CWOrders> orderList;
	private long phoneNumber;
	private User userWasher;

}
