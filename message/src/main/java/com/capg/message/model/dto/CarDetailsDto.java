package com.capg.message.model.dto;


public class  CarDetailsDto {
	
	private String name;
	private String plateNumber;
	private String color;
	private char lastRevision;
	private String customerId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public char getLastRevision() {
		return lastRevision;
	}
	public void setLastRevision(char lastRevision) {
		this.lastRevision = lastRevision;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	

}
