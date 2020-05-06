package com.capg.ocw.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.capg.ocw.model.Washer;

@Repository
public interface WasherRepository extends MongoRepository<Washer, String>{
	
	Washer findByWasherId(String washerId);
	
	List<Washer> findByStatus(String status);
}
