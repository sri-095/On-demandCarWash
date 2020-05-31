package com.capg.ocw.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.capg.ocw.model.Adderss;
import com.capg.ocw.model.CarDetails;
import com.capg.ocw.model.Customer;
import com.capg.ocw.model.dto.AdderssDto;
import com.capg.ocw.model.dto.CarDetailsDto;
import com.capg.ocw.model.dto.CustomerDto;
@Component
public class OCWUtils {
	
	
	public Customer saveCustomer(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setName(customerDto.getName());
		customer.setPhoneNumber(customerDto.getPhoneNumber());
		customer.setLastRevision(OCWConstants.YES_CHAR);
		customer.setStatus(OCWConstants.ACTIVE);
		customer.setLoggedIn(new Date());
		return customer;
		
	}
	public Adderss saveAddress(AdderssDto addressDto) {
		Adderss address = new Adderss();
		address.setAddressLine(addressDto.getAddressLine());
		address.setCity(addressDto.getCity());
		address.setLastRevision(OCWConstants.YES_CHAR);
		address.setZipCode(addressDto.getZipCode());
		address.setStatus(OCWConstants.ACTIVE);
		return address;
	}
	
	public List<CarDetails> saveCarDetails(List<CarDetailsDto> carDetailsDtos) {
		List<CarDetails> carDetailsList = new ArrayList<>();
		carDetailsDtos.stream().forEach(carDetailsDto -> {
			CarDetails carDetails = new CarDetails();
			carDetails.setPlateNumber(carDetailsDto.getPlateNumber());
			carDetails.setName(carDetailsDto.getName());
			carDetails.setLastRevision(OCWConstants.YES_CHAR);
			carDetails.setColor(carDetailsDto.getColor());
			carDetails.setCustomerId(carDetailsDto.getCustomerId());
			carDetails.setStatus(OCWConstants.ACTIVE);
			carDetailsList.add(carDetails);
		});
		return carDetailsList;
	}
	
	public String prepareId(Integer size,String startwith) {
		
		StringBuilder builder = new StringBuilder();
		builder.append(startwith)
			.append(1000+size+1);
		
		return builder.toString();
	}
}
