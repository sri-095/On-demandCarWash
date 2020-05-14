package com.capg.ocw.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capg.ocw.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>{
	
	List<Customer> findAll();
	
	Customer findByCustomerId(String customerId);

}
