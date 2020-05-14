package com.capg.ocw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ocw.model.dto.CustomerDto;
import com.capg.ocw.operation.CutomerOperation;

@RestController
@RequestMapping("/api/customer")
public class CustomerContoller {
	
	@Autowired
	private CutomerOperation cutomerOperation;
	
	@GetMapping("/fetchcustomer/{customerId}")
	public ResponseEntity<CustomerDto> getCustomerDetails(@PathVariable String customerId){
		return new ResponseEntity<>(cutomerOperation.getCustomerDetails(customerId), HttpStatus.OK);
	}
	
	@PostMapping("/updateCustomer")
	public ResponseEntity<String> updateCustomer(@RequestBody CustomerDto customerDto) {
		cutomerOperation.updateCustomer(customerDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
