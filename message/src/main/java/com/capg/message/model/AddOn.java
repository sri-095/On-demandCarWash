package com.capg.message.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AddOn extends CWObject {
	
	private String addOnId;
	private String description;
	private float cost;
	public String getAddOnId() {
		return addOnId;
	}
	public void setAddOnId(String addOnId) {
		this.addOnId = addOnId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}

}
