package com.capg.ocw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capg.ocw.model.Adderss;

public interface AddressRepository extends MongoRepository<Adderss, String>{

}
