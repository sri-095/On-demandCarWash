package com.capg.ocw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capg.ocw.model.User;

public interface UserRepository extends MongoRepository<User, String>{
	User findByUserId(String userId);
}
