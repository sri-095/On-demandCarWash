package com.capg.ocw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ocw.exception.OcwException;
import com.capg.ocw.model.dto.CustomerDto;
import com.capg.ocw.model.dto.UserDto;
import com.capg.ocw.operation.UserLoginOperation;

@RestController
public class UserLoginController {
	
	@Autowired
	private UserLoginOperation userLoginOperation;
	
	@PostMapping("/login")
	public ResponseEntity<String> validateLoginUser(@RequestBody UserDto userDto) throws OcwException{
		return new ResponseEntity<>(userLoginOperation.validateUser(userDto),HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@RequestBody CustomerDto customerDto) throws OcwException{
		return new ResponseEntity<>(userLoginOperation.saveUser(customerDto),HttpStatus.OK);
	}
}
