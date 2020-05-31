package com.capg.message.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ServicePlans")
public class CWServicePlan extends CWObject{
	
	private String planId;
	private String type;
	private double cost;
	private String description;
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
