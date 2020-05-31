package com.capg.message.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CarDetails")
public class CarDetails extends CWObject {
	
	private String plateNumber;
	private String color;
	private String customerId;
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
