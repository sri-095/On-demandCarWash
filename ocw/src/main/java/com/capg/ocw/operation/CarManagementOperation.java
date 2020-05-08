package com.capg.ocw.operation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.capg.ocw.exception.OcwException;
import com.capg.ocw.model.CarDetails;
import com.capg.ocw.model.dto.CarDetailsDto;
import com.capg.ocw.repository.CarDetailsRespository;
import com.capg.ocw.util.OCWConstants;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
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

	@Transactional(rollbackFor = OcwException.class)
	public void addOrUpdateCars(List<CarDetailsDto> carDetailsDtoList) {
		List<CarDetails> carDetailsList = new ArrayList<>();

		for(CarDetailsDto carDetailsDto : carDetailsDtoList) {
			CarDetails carDetails = carDetailsRespository.findByPlateNumber(carDetailsDto.getPlateNumber());
			if(null == carDetails) {
				log.info("Creating new car details");
				carDetails = new CarDetails();
				carDetails.setPlateNumber(carDetailsDto.getPlateNumber());
			}
				carDetails.setName(carDetailsDto.getName());
				carDetails.setLastRevision(OCWConstants.YES_CHAR);
				carDetails.setColor(carDetailsDto.getColor());
				carDetails.setCustomerId(carDetailsDto.getCustomerId());
				carDetailsList.add(carDetails);
		}
		carDetailsRespository.saveAll(carDetailsList);
	}

	@Transactional(rollbackFor = OcwException.class)
	public List<CarDetailsDto> activeOrInActiveCar(String status) throws OcwException {
		List<CarDetails> carDetailsDb = carDetailsRespository.findByStatus(status);
		List<CarDetailsDto> carDetailsDtoList = new ArrayList<>();
		carDetailsDb.stream().forEach(carDetails -> {
			CarDetailsDto carDetailsDto = new CarDetailsDto();
			carDetailsDto.setName(carDetails.getName());
			carDetailsDto.setColor(carDetails.getColor());
			carDetailsDto.setPlateNumber(carDetails.getPlateNumber());
			carDetailsDtoList.add(carDetailsDto);
		});
		log.info("Fetched "+status+" cars");
		return carDetailsDtoList;
	}

}
