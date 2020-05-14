package com.capg.ocw.model.dto;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class WashPackageDto {
	
	private CarDetailsDto carDetailsDto;
	private ServicePlanDto servicePlanDto;
	private List<AddOnDto> addOnDtoList;
	private String notes;
	private Date orderedDate;
	private Date scheduleDate;
}
