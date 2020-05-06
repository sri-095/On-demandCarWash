package com.capg.ocw.operation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.capg.ocw.exception.OcwException;
import com.capg.ocw.model.CWServicePlan;
import com.capg.ocw.model.dto.ServicePlanDto;
import com.capg.ocw.repository.ServicePlanRepository;

@Component
public class ServicePlanOperation {

	@Autowired
	private ServicePlanRepository servicePlanRepository;

	public List<ServicePlanDto> getAllServicePlans() {
		List<ServicePlanDto> servicePlanDtos = new ArrayList<>();
		List<CWServicePlan> planList = servicePlanRepository.findAll();
		planList.stream().forEach(plan -> {
			ServicePlanDto planDto = new ServicePlanDto();
			planDto.setName(plan.getName());
			planDto.setDescription(plan.getDescription());
			planDto.setCost(plan.getCost());
			planDto.setType(plan.getType());
			planDto.setPlanId(plan.getPlanId());
			servicePlanDtos.add(planDto);
		});
		return servicePlanDtos;
	}
	
	@Transactional(rollbackFor = OcwException.class)
	public List<ServicePlanDto> addOrUpdateServicePlans(List<ServicePlanDto> planDtos) {
		List<CWServicePlan> cwServicePlans = new ArrayList<>();
		for(ServicePlanDto planDto : planDtos) {
			CWServicePlan servicePlan = servicePlanRepository.findByPlanId(planDto.getPlanId());
			if(null == servicePlan) {
				servicePlan = new CWServicePlan();
				servicePlan.setPlanId(planDto.getPlanId());
			}
			servicePlan.setName(planDto.getName());
			servicePlan.setDescription(planDto.getDescription());
			servicePlan.setCost(planDto.getCost());
			servicePlan.setType(planDto.getType());
			cwServicePlans.add(servicePlan);
		}
		servicePlanRepository.saveAll(cwServicePlans);
		return getAllServicePlans();
	}

	@Transactional(rollbackFor = OcwException.class)
	public List<ServicePlanDto> activateOrInactiveServicePlan(String status) throws OcwException {
		List<ServicePlanDto> servicePlanDtos = new ArrayList<>();
		List<CWServicePlan> servicePlans = servicePlanRepository.findByStatus(status);
		servicePlans.stream().forEach(plan -> {
			ServicePlanDto planDto = new ServicePlanDto();
			planDto.setName(plan.getName());
			planDto.setDescription(plan.getDescription());
			planDto.setCost(plan.getCost());
			planDto.setType(plan.getType());
			planDto.setPlanId(plan.getPlanId());
			servicePlanDtos.add(planDto);
		});
		return servicePlanDtos;
	}


}
