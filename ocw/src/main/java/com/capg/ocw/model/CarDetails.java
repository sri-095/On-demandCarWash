package com.capg.ocw.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "CarDetails")
@Getter
@Setter
public class CarDetails extends CWObject {
	
	private String plateNumber;
	private String color;
	private String customerId;

}
