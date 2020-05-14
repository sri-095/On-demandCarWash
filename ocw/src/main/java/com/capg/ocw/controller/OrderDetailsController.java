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
import com.capg.ocw.model.dto.AssignWasherDto;
import com.capg.ocw.model.dto.OrdersDetailsDto;
import com.capg.ocw.model.dto.WashPackageDto;
import com.capg.ocw.operation.OrderDetailsOperation;

@RestController
@RequestMapping("/api/orderDetails")
public class OrderDetailsController {

	@Autowired
	private OrderDetailsOperation orderDetailsOperation;

	@GetMapping("/getOrders")
	public ResponseEntity<List<OrdersDetailsDto>> getAllOrderDetails() {
		return new ResponseEntity<>(orderDetailsOperation.getAllOrderDetails(),HttpStatus.OK);
	}

	@PutMapping("/assignWasher")
	public ResponseEntity<String> assignWasher(@RequestBody AssignWasherDto assignWasherDto){
		orderDetailsOperation.assignWasher(assignWasherDto);
		return new ResponseEntity<>("Assigned Washer",HttpStatus.OK);
	}

	@GetMapping("/getOrders/{status}")
	public ResponseEntity<List<OrdersDetailsDto>> getOrdersByStatus(@PathVariable String status) {
		return new ResponseEntity<>(orderDetailsOperation.getOrdersByStatus(status),HttpStatus.OK);
	}

	@GetMapping("/getOrders/{orderid}")
	public ResponseEntity<OrdersDetailsDto> getOrders(@PathVariable String orderid) throws OcwException {
		return new ResponseEntity<>(orderDetailsOperation.getOrdersByOrderId(orderid),HttpStatus.OK);
	}
	@GetMapping("/getOrders/{customerId}")
	public ResponseEntity<List<OrdersDetailsDto>> getOrdersByCustomerId(@PathVariable String customerId) throws OcwException {
		return new ResponseEntity<>(orderDetailsOperation.getOrdersByCustomerId(customerId),HttpStatus.OK);
	}

	@PostMapping("/bookCarWash")
	public ResponseEntity<String> bookCarWash(@RequestBody WashPackageDto washPackageDto) {
		orderDetailsOperation.bookCarWash(washPackageDto);
		return new ResponseEntity<>("Booking confirmed",HttpStatus.OK);
	}

	@GetMapping("/notifyuser/{status}")
	public ResponseEntity<String> notifyUser(@PathVariable String status) throws OcwException {
		return new ResponseEntity<>(orderDetailsOperation.notifyUser(status),HttpStatus.OK);
	}

}
