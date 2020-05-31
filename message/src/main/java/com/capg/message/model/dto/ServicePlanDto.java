package com.capg.message.model.dto;

public class ServicePlanDto {
	
	private String planId;
	private String type;
	private double cost;
	private String description;
	private String name;
	private char lastRevision;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getLastRevision() {
		return lastRevision;
	}
	public void setLastRevision(char lastRevision) {
		this.lastRevision = lastRevision;
	}
	

}
