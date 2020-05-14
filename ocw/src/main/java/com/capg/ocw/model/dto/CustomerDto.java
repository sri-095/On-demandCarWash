package com.capg.ocw.model.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class CustomerDto {
	
	private String customerId;
	private List<CarDetailsDto> carDetails;
	private String name;
	private long phoneNumber;
	private AdderssDto address;
	private UserDto customerUser;
	private Date registedDate;
}
