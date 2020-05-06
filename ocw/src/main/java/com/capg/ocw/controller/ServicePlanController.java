package com.capg.ocw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ocw.exception.OcwException;
import com.capg.ocw.model.dto.ServicePlanDto;
import com.capg.ocw.operation.ServicePlanOperation;
import com.capg.ocw.util.OCWConstants;

@RestController
@RequestMapping("/api/servicePlan")
public class ServicePlanController {
	@Autowired
	private ServicePlanOperation servicePlanOperation;
	
	@GetMapping("/getAllServicePlans")
	public ResponseEntity<List<ServicePlanDto>> fetchAllServicePlans() {
		List<ServicePlanDto> servicePlans = servicePlanOperation.getAllServicePlans();
		return new ResponseEntity<>(servicePlans, HttpStatus.OK);
	}
	
	@PostMapping("/addOrUpdateServicePlan")
	public ResponseEntity<List<ServicePlanDto>> addOrUpdateServicePlan(@RequestBody List<ServicePlanDto> servicePlans) {
		return new ResponseEntity<>(servicePlanOperation.addOrUpdateServicePlans(servicePlans), HttpStatus.OK);
	}
	
	@GetMapping("/{status}")
	public ResponseEntity<List<ServicePlanDto>> activateOrInactiveServicePlan(@PathVariable String status) throws OcwException {
		if(OCWConstants.ACTIVE.equalsIgnoreCase(status) || OCWConstants.INACTIVE.equalsIgnoreCase(status))
			return new ResponseEntity<>(servicePlanOperation.activateOrInactiveServicePlan(status) , HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
