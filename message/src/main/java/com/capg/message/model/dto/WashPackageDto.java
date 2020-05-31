package com.capg.message.model.dto;

import java.sql.Date;
import java.util.List;

public class WashPackageDto {
	
	private CarDetailsDto carDetailsDto;
	private ServicePlanDto servicePlanDto;
	private List<AddOnDto> addOnDtoList;
	private String notes;
	private Date orderedDate;
	private Date scheduleDate;
	private double cost;
	
	public CarDetailsDto getCarDetailsDto() {
		return carDetailsDto;
	}
	public void setCarDetailsDto(CarDetailsDto carDetailsDto) {
		this.carDetailsDto = carDetailsDto;
	}
	public ServicePlanDto getServicePlanDto() {
		return servicePlanDto;
	}
	public void setServicePlanDto(ServicePlanDto servicePlanDto) {
		this.servicePlanDto = servicePlanDto;
	}
	public List<AddOnDto> getAddOnDtoList() {
		return addOnDtoList;
	}
	public void setAddOnDtoList(List<AddOnDto> addOnDtoList) {
		this.addOnDtoList = addOnDtoList;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Date getOrderedDate() {
		return orderedDate;
	}
	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}
	public Date getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
}
