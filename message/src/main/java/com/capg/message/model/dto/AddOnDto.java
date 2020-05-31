package com.capg.message.model.dto;

public class AddOnDto {
	
	private String addOnId;
	private String type;
	private float cost;
	private String description;
	private String name;
	private char lastRevision;
	public String getAddOnId() {
		return addOnId;
	}
	public void setAddOnId(String addOnId) {
		this.addOnId = addOnId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
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
