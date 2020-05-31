package com.capg.message.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.message.operation.RabbitMQOperation;


@RestController
@RequestMapping(value = "/api")
public class RabbitMQController {

	@Autowired
	RabbitMQOperation rabbitMQOperation;

	@PostMapping(value = "/notifyUser")
	public String notifyUser(@RequestBody String msg) {
	
		rabbitMQOperation.send(msg);

		return "Message sent to the RabbitMQ Successfully";
	}

}

