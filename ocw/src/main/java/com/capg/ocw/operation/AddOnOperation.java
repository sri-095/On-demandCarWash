package com.capg.ocw.operation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.capg.ocw.exception.OcwException;
import com.capg.ocw.model.AddOn;
import com.capg.ocw.model.dto.AddOnDto;
import com.capg.ocw.repository.AddOnRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AddOnOperation {

	@Autowired
	private AddOnRepository addOnRepository;

	public List<AddOnDto> getAllAddOns() {
		List<AddOnDto> addOnDtos = new ArrayList<>();
		List<AddOn> addOns = addOnRepository.findAll();
		addOns.stream().forEach(addOn -> {
			AddOnDto addOnDto = new AddOnDto();
			addOnDto.setName(addOn.getName());
			addOnDto.setDescription(addOn.getDescription());
			addOnDto.setCost(addOn.getCost());
			addOnDto.setAddOnId(addOn.getAddOnId());
			addOnDtos.add(addOnDto);
		});
		log.info("AddOn fetched Sucessfully");
		return addOnDtos;
	}
	
	@Transactional(rollbackFor = OcwException.class)
	public List<AddOnDto> addOrUpdateAddOn(List<AddOnDto> addOnDtos) {
		List<AddOn> addOns = new ArrayList<>();
		for(AddOnDto addOnDto : addOnDtos) {
			AddOn addOn = addOnRepository.findByAddOnId(addOnDto.getAddOnId());
			if(null == addOn) {
				addOn = new AddOn();
				addOn.setAddOnId(addOnDto.getAddOnId());
				log.info("New Add on created Sucessfully");
			}
			addOn.setName(addOnDto.getName());
			addOn.setDescription(addOnDto.getDescription());
			addOn.setCost(addOnDto.getCost());
			addOns.add(addOn);
			log.info("Add on updated Sucessfully");
		}
		addOnRepository.saveAll(addOns);
		return getAllAddOns();
	}

	public List<AddOnDto> activaOrInActiveAddOn(String status) throws OcwException {
		List<AddOn> addOns = addOnRepository.findByStatus(status);
		List<AddOnDto> addOnDtos = new ArrayList<>();
		
		if(CollectionUtils.isEmpty(addOns))
			log.error("No " + status +" Addons found!");
		else {
		addOns.stream().forEach(addOn -> {
			AddOnDto addOnDto = new AddOnDto();
			addOnDto.setName(addOn.getName());
			addOnDto.setDescription(addOn.getDescription());
			addOnDto.setCost(addOn.getCost());
			addOnDto.setAddOnId(addOn.getAddOnId());
			addOnDtos.add(addOnDto);
		});
		log.info("Fetched all " + status +" Addons ");
		}
		return addOnDtos;
	}

}
