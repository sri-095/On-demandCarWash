package com.capg.ocw.model.dto;

import java.util.List;

import com.capg.ocw.model.CWOrders;

import lombok.Data;

@Data
public class WasherDto {
	
	private String washerId;
	private String emailId;
	private float ratings;
	private List<CWOrders> orderList;
	private String name;
	private long phoneNumber;


}
