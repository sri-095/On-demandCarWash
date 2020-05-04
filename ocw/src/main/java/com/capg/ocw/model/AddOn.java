package com.capg.ocw.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class AddOn extends CWObject {
	
	private String addOnId;
	private String description;
	private float cost;

}
