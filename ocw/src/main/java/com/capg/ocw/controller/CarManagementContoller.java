package com.capg.ocw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ocw.exception.OcwException;
import com.capg.ocw.model.dto.CarDetailsDto;
import com.capg.ocw.operation.CarManagementOperation;
import com.capg.ocw.util.OCWConstants;
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/carmanagement")
public class CarManagementContoller {

	@Autowired
	private CarManagementOperation carManagementOperation;
	
	@GetMapping("/fetchAllCars")
	public ResponseEntity<List<CarDetailsDto>> fetchAllCarDetails(){
		return new ResponseEntity<>(carManagementOperation.fetchAllCarDetails(), HttpStatus.OK);
	}
	
	@PostMapping("/addCar")
	public ResponseEntity<String> addOrUpdateCars(@RequestBody List<CarDetailsDto> carDetailsDto) {
		carManagementOperation.addOrUpdateCars(carDetailsDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/{status}")
	public ResponseEntity<List<CarDetailsDto>> activeOrInActiveCar(@PathVariable String status) throws OcwException {
		if(OCWConstants.ACTIVE.equalsIgnoreCase(status) || (OCWConstants.INACTIVE.equalsIgnoreCase(status)))
			return new ResponseEntity<>(carManagementOperation.activeOrInActiveCar(status),HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
