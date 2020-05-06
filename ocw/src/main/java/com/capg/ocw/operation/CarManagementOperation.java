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

	@Transactional(rollbackFor = OcwException.class)
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
				newCarDetails.setLastRevision(OCWConstants.YES_CHAR);
				carDetailsList.add(newCarDetails);
			}
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
		return carDetailsDtoList;
	}

}
