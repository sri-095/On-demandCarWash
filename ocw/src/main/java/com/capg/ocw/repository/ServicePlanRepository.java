package com.capg.ocw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capg.ocw.model.CWServicePlan;

public interface ServicePlanRepository extends MongoRepository<CWServicePlan, String>{

	CWServicePlan findByPlanId(String planId);
	
	

}
