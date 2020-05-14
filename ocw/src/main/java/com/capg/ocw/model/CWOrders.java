package com.capg.ocw.model;


import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.capg.ocw.model.dto.AddOnDto;
import com.capg.ocw.model.dto.CarDetailsDto;
import com.capg.ocw.model.dto.ServicePlanDto;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "CWOrders")
@Getter
@Setter
public class CWOrders extends CWObject{
	
	private String orderId;
	private String type;
	private double cost;
	private char washerAssigned;
	private String washerId;
	@DateTimeFormat(iso = ISO.DATE)
	private Date orderedDate;
	private String notes;
	private CarDetailsDto carDetailsDto;
	private ServicePlanDto servicePlanDto;
	private List<AddOnDto> addOnDto;
	private String customerId;
	@DateTimeFormat(iso = ISO.DATE)
	private Date scheduleDate;
}
