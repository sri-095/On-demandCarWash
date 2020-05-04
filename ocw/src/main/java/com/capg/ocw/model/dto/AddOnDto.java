package com.capg.ocw.model.dto;

import lombok.Data;

@Data
public class AddOnDto {
	
	private String addOnId;
	private String type;
	private float cost;
	private String description;
	private String name;
	private char lastRevision;

}
