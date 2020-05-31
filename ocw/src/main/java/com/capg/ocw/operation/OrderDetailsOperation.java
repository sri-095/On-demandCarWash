package com.capg.ocw.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.capg.ocw.exception.OcwException;
import com.capg.ocw.model.CWOrders;
import com.capg.ocw.model.Washer;
import com.capg.ocw.model.dto.AddOnDto;
import com.capg.ocw.model.dto.AssignWasherDto;
import com.capg.ocw.model.dto.OrdersDetailsDto;
import com.capg.ocw.model.dto.ServicePlanDto;
import com.capg.ocw.model.dto.WashPackageDto;
import com.capg.ocw.repository.OrderDetailsRespository;
import com.capg.ocw.repository.WasherRepository;
import com.capg.ocw.service.MessageService;
import com.capg.ocw.util.OCWConstants;
import com.capg.ocw.util.OCWUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderDetailsOperation {

	@Autowired
	private OrderDetailsRespository orderDetailsRespository;
	
	@Autowired
	private WasherRepository washerRepository;
	
	@Autowired
	private OCWUtils ocwUtils;
	
	@Autowired
	private MessageService messageService;
	
	public List<OrdersDetailsDto> getAllOrderDetails() {
		List<OrdersDetailsDto> ordersDetailsDtos = new ArrayList<>();
		List<CWOrders> orderDetailsDb = orderDetailsRespository.findAll();
		for(CWOrders cwOrders : orderDetailsDb) {
			OrdersDetailsDto detailsDto = new OrdersDetailsDto();
			detailsDto.setCost(cwOrders.getCost());
			detailsDto.setCustomerId(cwOrders.getCarDetailsDto().getCustomerId());
			detailsDto.setOrderId(cwOrders.getOrderId());
			detailsDto.setStatus(cwOrders.getStatus());
			detailsDto.setType(cwOrders.getType());
			detailsDto.setCarDetailsDto(cwOrders.getCarDetailsDto());
			detailsDto.setAddOnDtos(cwOrders.getAddOnDto());
			detailsDto.setNotes(cwOrders.getNotes());
			detailsDto.setServicePlanDto(cwOrders.getServicePlanDto());
			
			detailsDto.setWasherAssigned(cwOrders.getWasherAssigned());
			ordersDetailsDtos.add(detailsDto);
		}
		return ordersDetailsDtos;
	}

	public void assignWasher(AssignWasherDto assignWasherDto) {
		
		CWOrders orders = orderDetailsRespository.findByOrderId(assignWasherDto.getOrderId());
		orders.setWasherAssigned(OCWConstants.YES_CHAR);
		orders.setStatus("Assigned");
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
			detailsDto.setCustomerId(cwOrders.getCarDetailsDto().getCustomerId());
			detailsDto.setOrderId(cwOrders.getOrderId());
			detailsDto.setStatus(cwOrders.getStatus());
			detailsDto.setType(cwOrders.getType());
			detailsDto.setWasherAssigned(cwOrders.getWasherAssigned());
			detailsDto.setCarDetailsDto(cwOrders.getCarDetailsDto());
			detailsDto.setAddOnDtos(cwOrders.getAddOnDto());
			detailsDto.setNotes(cwOrders.getNotes());
			detailsDto.setServicePlanDto(cwOrders.getServicePlanDto());
			ordersDetailsDtos.add(detailsDto);
		}
		return ordersDetailsDtos;
	}
	
	public OrdersDetailsDto getOrdersByOrderId(String orderId) throws OcwException {
		OrdersDetailsDto detailsDto = null;
		CWOrders orderDetailsDb = orderDetailsRespository.findByOrderId(orderId);
		if(null != orderDetailsDb) {
			detailsDto = new OrdersDetailsDto();
			detailsDto.setCost(orderDetailsDb.getCost());
			detailsDto.setCustomerId(orderDetailsDb.getCarDetailsDto().getCustomerId());
			detailsDto.setOrderId(orderDetailsDb.getOrderId());
			detailsDto.setStatus(orderDetailsDb.getStatus());
			detailsDto.setType(orderDetailsDb.getType());
			detailsDto.setWasherAssigned(orderDetailsDb.getWasherAssigned());
			detailsDto.setCarDetailsDto(orderDetailsDb.getCarDetailsDto());
			detailsDto.setAddOnDtos(orderDetailsDb.getAddOnDto());
			detailsDto.setNotes(orderDetailsDb.getNotes());
			detailsDto.setServicePlanDto(orderDetailsDb.getServicePlanDto());
		}else {
			String erromsg = "Order not found. Please enter correct id";
			log.error(erromsg);
			throw new OcwException(erromsg);
		}
		return detailsDto;
	}

	public List<OrdersDetailsDto> getOrdersByCustomerId(String customerId) {
		List<OrdersDetailsDto> ordersDetailsDtos = new ArrayList<>();
		List<CWOrders> orderDetailsDb = orderDetailsRespository.findByCustomerId(customerId);
		for(CWOrders cwOrders : orderDetailsDb) {
			OrdersDetailsDto detailsDto = new OrdersDetailsDto();
			detailsDto.setCost(cwOrders.getCost());
			detailsDto.setCustomerId(cwOrders.getCustomerId());
			detailsDto.setOrderId(cwOrders.getOrderId());
			detailsDto.setStatus(cwOrders.getStatus());
			detailsDto.setType(cwOrders.getType());
			detailsDto.setWasherAssigned(cwOrders.getWasherAssigned());
			detailsDto.setCarDetailsDto(cwOrders.getCarDetailsDto());
			detailsDto.setAddOnDtos(cwOrders.getAddOnDto());
			detailsDto.setNotes(cwOrders.getNotes());
			detailsDto.setServicePlanDto(cwOrders.getServicePlanDto());
			ordersDetailsDtos.add(detailsDto);
		}
		return ordersDetailsDtos;
	}
	
	public String bookCarWash(WashPackageDto packageDto) {
		CWOrders orders = new CWOrders();
		orders.setOrderId(ocwUtils.prepareId(orderDetailsRespository.findAll().size(), "O"));
		orders.setCarDetailsDto(packageDto.getCarDetailsDto());
		orders.setCustomerId(packageDto.getCarDetailsDto().getCustomerId());
		orders.setAddOnDto(packageDto.getAddOnDtoList());
		orders.setNotes(packageDto.getNotes());
		orders.setServicePlanDto(packageDto.getServicePlanDto());
		orders.setStatus("pending");
		orders.setCost(cost(packageDto.getAddOnDtoList(),packageDto.getServicePlanDto()));
		orders.setOrderedDate(new Date());
		orders.setWasherAssigned(OCWConstants.NO_CHAR);
		orders.setLastRevision(OCWConstants.YES_CHAR);
		orders.setScheduleDate(packageDto.getScheduleDate() == null? packageDto.getOrderedDate() : packageDto.getScheduleDate());
		orderDetailsRespository.save(orders);
		//send notification for washer
		return "Booking confirmed. Your Order Id is " + orders.getOrderId();
	}

	private double cost(List<AddOnDto> addOnDtoList, ServicePlanDto servicePlanDto) {
		
		double cost = servicePlanDto.getCost(); 
		if(!CollectionUtils.isEmpty(addOnDtoList)) {
			for(AddOnDto addOnDto : addOnDtoList)
				cost = addOnDto.getCost() + cost;
		}
		return cost;
	}

	public String notifyUser(String status) {
		String msg= "The washer has " +status+"ed ";
		
		return messageService.sendUserWasherUpdate(msg);
	}
	
	

}
