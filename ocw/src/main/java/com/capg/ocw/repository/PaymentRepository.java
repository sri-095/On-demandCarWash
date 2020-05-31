package com.capg.ocw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capg.ocw.model.PaymentDetails;

public interface PaymentRepository extends MongoRepository<PaymentDetails, String>{

}
