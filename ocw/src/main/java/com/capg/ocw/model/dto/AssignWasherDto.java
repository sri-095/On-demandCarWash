package com.capg.ocw.model.dto;

import lombok.Data;

@Data
public class AssignWasherDto {
	
	private String orderId;
	private String customerId;
	private String type;
	private String washerId;
	private char lastRevision;

}
