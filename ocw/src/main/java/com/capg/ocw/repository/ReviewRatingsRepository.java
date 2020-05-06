package com.capg.ocw.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capg.ocw.model.ReviewRatings;

public interface ReviewRatingsRepository extends MongoRepository<ReviewRatings, String>{
	
	List<ReviewRatings> findByWasherId(String washerId);
	List<ReviewRatings> findByCustomerId(String customerId);

}
