package com.capg.ocw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ocw.exception.OcwException;
import com.capg.ocw.model.dto.AddOnDto;
import com.capg.ocw.operation.AddOnOperation;
import com.capg.ocw.util.OCWConstants;

@RestController
@RequestMapping("/api/addon")
public class AddOnController {
	@Autowired
	private AddOnOperation addOnOperation;
	
	@GetMapping("/getAllServicePlans")
	public ResponseEntity<List<AddOnDto>> fetchAllAddOns() {
		List<AddOnDto> servicePlans = addOnOperation.getAllAddOns();
		return new ResponseEntity<>(servicePlans, HttpStatus.OK);
	}
	
	@PostMapping("/addOrUpdateServicePlan")
	public ResponseEntity<List<AddOnDto>> addOrUpdateAddOn(List<AddOnDto> addOnDtos) {
		return new ResponseEntity<>(addOnOperation.addOrUpdateAddOn(addOnDtos), HttpStatus.OK);
	}
	
	@PutMapping("/{status}")
	public ResponseEntity<String> activateOrDeactivateAddOn(@RequestBody AddOnDto addOnDto,@PathVariable String status) throws OcwException {
		if(OCWConstants.ACTIVE.equalsIgnoreCase(status))
			return new ResponseEntity<>(addOnOperation.activateAddOn(addOnDto) , HttpStatus.OK);
		else if(OCWConstants.INACTIVE.equalsIgnoreCase(status))
			return new ResponseEntity<>(addOnOperation.deactivateAddOn(addOnDto),HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
