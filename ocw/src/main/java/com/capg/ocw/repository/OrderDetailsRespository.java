package com.capg.ocw.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.capg.ocw.model.CWOrders;

@Repository
public interface OrderDetailsRespository extends MongoRepository<CWOrders, String> {

	CWOrders findByOrderId(String orderId);

	List<CWOrders> findByStatus(String status);
}
