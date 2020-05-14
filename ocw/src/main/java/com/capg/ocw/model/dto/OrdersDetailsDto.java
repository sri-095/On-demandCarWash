package com.capg.ocw.model.dto;

import lombok.Data;

@Data
public class OrdersDetailsDto {
	
	private String orderId;
	private String customerId;
	private String type;
	private double cost;
	private char washerAssigned;
	private String status;
	private CarDetailsDto carDetailsDto;
	private ServicePlanDto servicePlanDto;
	private AddOnDto addOnDto;
	private String notes;
}
