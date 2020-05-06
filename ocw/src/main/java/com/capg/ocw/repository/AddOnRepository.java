package com.capg.ocw.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capg.ocw.model.AddOn;

public interface AddOnRepository extends MongoRepository<AddOn, String>{

	AddOn findByAddOnId(String addOnId);
	
	List<AddOn> findByStatus(String status);
	
	
}
