package com.capg.ocw.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "ServicePlans")
@Getter
@Setter
public class CWServicePlan extends CWObject{
	
	private String planId;
	private String type;
	private double cost;
	private String description;
}
