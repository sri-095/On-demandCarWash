package com.capg.ocw.operation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capg.ocw.model.CarDetails;
import com.capg.ocw.model.dto.CarDetailsDto;
import com.capg.ocw.repository.CarDetailsRespository;

@Component
public class CarManagementOperation {
	
	@Autowired
	private CarDetailsRespository carDetailsRespository;

	public List<CarDetailsDto> fetchAllCarDetails() {
		List<CarDetailsDto> carDetailsDtoList = new ArrayList<>();
		List<CarDetails> carDetailsDb = carDetailsRespository.findAll();
		carDetailsDb.stream().forEach(carDetails -> {
			CarDetailsDto carDetailsDto = new CarDetailsDto();
			carDetailsDto.setName(carDetails.getName());
			carDetailsDto.setColor(carDetails.getColor());
			carDetailsDto.setPlateNumber(carDetails.getPlateNumber());
			carDetailsDtoList.add(carDetailsDto);
		});
		return carDetailsDtoList;
	}

	public void addOrUpdateCars(List<CarDetailsDto> carDetailsDtoList) {
		List<CarDetails> carDetailsList = new ArrayList<>();
		
		for(CarDetailsDto carDetailsDto : carDetailsDtoList) {
			CarDetails carDetails = carDetailsRespository.findByPlateNumber(carDetailsDto.getPlateNumber());
			if(null != carDetails) {
				carDetails.setName(carDetailsDto.getName());
				carDetails.setColor(carDetailsDto.getColor());
				carDetails.setPlateNumber(carDetailsDto.getPlateNumber());
				carDetailsList.add(carDetails);
			}
			else {
				CarDetails newCarDetails = new CarDetails();
				newCarDetails.setName(carDetailsDto.getName());
				newCarDetails.setPlateNumber(carDetailsDto.getPlateNumber());
				carDetailsList.add(newCarDetails);
			}
		}
		carDetailsRespository.saveAll(carDetailsList);
	}
	
	
	
	
	
	
}
