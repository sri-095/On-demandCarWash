package com.capg.ocw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate geRestTemplate() {
		return new RestTemplate();
	}
	
	public String sendUserWasherUpdate(String status) {
		String baseUrl = "http://localhost:8081/api/notifyUser";
		
		return restTemplate.postForObject(baseUrl,status,String.class);
	}

}
