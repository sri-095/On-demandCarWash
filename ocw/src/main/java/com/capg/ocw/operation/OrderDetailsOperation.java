package com.capg.ocw.operation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.capg.ocw.model.CWOrders;
import com.capg.ocw.model.Washer;
import com.capg.ocw.model.dto.AssignWasherDto;
import com.capg.ocw.model.dto.OrdersDetailsDto;
import com.capg.ocw.repository.OrderDetailsRespository;
import com.capg.ocw.repository.WasherRepository;
import com.capg.ocw.util.OCWConstants;

@Component
public class OrderDetailsOperation {

	@Autowired
	private OrderDetailsRespository orderDetailsRespository;
	
	@Autowired
	private WasherRepository washerRepository;
	
	public List<OrdersDetailsDto> getAllOrderDetails() {
		List<OrdersDetailsDto> ordersDetailsDtos = new ArrayList<>();
		List<CWOrders> orderDetailsDb = orderDetailsRespository.findAll();
		for(CWOrders cwOrders : orderDetailsDb) {
			OrdersDetailsDto detailsDto = new OrdersDetailsDto();
			detailsDto.setCost(cwOrders.getCost());
			detailsDto.setCustomerId(cwOrders.getCustomerId());
			detailsDto.setOrderId(cwOrders.getOrderId());
			detailsDto.setStatus(cwOrders.getStatus());
			detailsDto.setType(cwOrders.getType());
			detailsDto.setWasherAssigned(cwOrders.getWasherAssigned());
			ordersDetailsDtos.add(detailsDto);
		}
		return ordersDetailsDtos;
	}

	public void assignWasher(AssignWasherDto assignWasherDto) {
		
		CWOrders orders = orderDetailsRespository.findByOrderId(assignWasherDto.getOrderId());
		orders.setWasherAssigned(OCWConstants.YES_CHAR);
		orders.setStatus("UnderProcess");
		orders.setWasherId(assignWasherDto.getWasherId());
		orderDetailsRespository.save(orders);
		
		Washer washer = washerRepository.findByWasherId(assignWasherDto.getWasherId());
		List<CWOrders> orderList = new ArrayList<>();
		if(!CollectionUtils.isEmpty(washer.getOrderList()))
			orderList = washer.getOrderList();
		orderList.add(orders);
		washer.setOrderList(orderList);
		washerRepository.save(washer);
	}

	public List<OrdersDetailsDto> getOrdersByStatus(String status) {
		List<OrdersDetailsDto> ordersDetailsDtos = new ArrayList<>();
		List<CWOrders> orderDetailsDb = orderDetailsRespository.findByStatus(status);
		for(CWOrders cwOrders : orderDetailsDb) {
			OrdersDetailsDto detailsDto = new OrdersDetailsDto();
			detailsDto.setCost(cwOrders.getCost());
			detailsDto.setCustomerId(cwOrders.getCustomerId());
			detailsDto.setOrderId(cwOrders.getOrderId());
			detailsDto.setStatus(cwOrders.getStatus());
			detailsDto.setType(cwOrders.getType());
			detailsDto.setWasherAssigned(cwOrders.getWasherAssigned());
			ordersDetailsDtos.add(detailsDto);
		}
		return ordersDetailsDtos;
	}
}
