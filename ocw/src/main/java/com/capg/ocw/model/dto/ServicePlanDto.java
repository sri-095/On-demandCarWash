package com.capg.ocw.model.dto;

import lombok.Data;

@Data
public class ServicePlanDto {
	
	private String planId;
	private String type;
	private double cost;
	private String description;
	private String name;
	private char lastRevision;
	

}
