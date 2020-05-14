package com.capg.ocw.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
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
	@DBRef
	private List<CWOrders> orderList;
	private long phoneNumber;
	@DBRef
	private User userWasher;

}
