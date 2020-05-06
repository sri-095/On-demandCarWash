package com.capg.ocw.model.dto;

import lombok.Data;

@Data
public class ReviewRatingDto {
	
	private String customerId;
	private String washerId;
	private float ratings;
	private String reviews;

}
