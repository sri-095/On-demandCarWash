package com.capg.message.model;


import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection = "CWOrders")
public class CWOrders extends CWObject{
	
	private String orderId;
	private String type;
	private double cost;
	private char washerAssigned;
	private String washerId;
	@DateTimeFormat(iso = ISO.DATE)
	private Date orderedDate;
	private String notes;
	private CarDetails carDetailsDto;
	private CWServicePlan servicePlanDto;
	private List<AddOn> addOnDto;
	private String customerId;
	@DateTimeFormat(iso = ISO.DATE)
	private Date scheduleDate;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public char getWasherAssigned() {
		return washerAssigned;
	}
	public void setWasherAssigned(char washerAssigned) {
		this.washerAssigned = washerAssigned;
	}
	public String getWasherId() {
		return washerId;
	}
	public void setWasherId(String washerId) {
		this.washerId = washerId;
	}
	public Date getOrderedDate() {
		return orderedDate;
	}
	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public CarDetails getCarDetailsDto() {
		return carDetailsDto;
	}
	public void setCarDetailsDto(CarDetails carDetailsDto) {
		this.carDetailsDto = carDetailsDto;
	}
	public CWServicePlan getServicePlanDto() {
		return servicePlanDto;
	}
	public void setServicePlanDto(CWServicePlan servicePlanDto) {
		this.servicePlanDto = servicePlanDto;
	}
	public List<AddOn> getAddOnDto() {
		return addOnDto;
	}
	public void setAddOnDto(List<AddOn> addOnDto) {
		this.addOnDto = addOnDto;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Date getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	
	
}
