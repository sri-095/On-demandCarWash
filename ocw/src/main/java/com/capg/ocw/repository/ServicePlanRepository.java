package com.capg.ocw.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.capg.ocw.model.CWServicePlan;

public interface ServicePlanRepository extends MongoRepository<CWServicePlan, String>{

	CWServicePlan findByPlanId(String planId);
	
	@Query("{'status : ?0'}")
	List<CWServicePlan> findByStatus(String status);
	
	

}
