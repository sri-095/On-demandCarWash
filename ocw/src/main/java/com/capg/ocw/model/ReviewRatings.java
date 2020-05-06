package com.capg.ocw.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class ReviewRatings {
	
	private String customerId;
	private String washerId;
	private float ratings;
	private String reviews;

}
