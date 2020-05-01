package com.capg.ocw.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.capg.ocw.model.CarDetails;
import java.lang.String;

@Repository
public interface CarDetailsRespository extends MongoRepository<CarDetails, String>{

	List<CarDetails> findAll();
	//List<CarDetails> findByCustomerId(String customerId);
	CarDetails findByPlateNumber(String platenumber);
}
