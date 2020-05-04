package com.capg.ocw.operation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.capg.ocw.exception.OcwException;
import com.capg.ocw.model.AddOn;
import com.capg.ocw.model.dto.AddOnDto;
import com.capg.ocw.repository.AddOnRepository;
import com.capg.ocw.util.OCWConstants;

@Component
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
			}
			addOn.setName(addOnDto.getName());
			addOn.setDescription(addOnDto.getDescription());
			addOn.setCost(addOnDto.getCost());
			addOns.add(addOn);
		}
		addOnRepository.saveAll(addOns);
		return getAllAddOns();
	}

	@Transactional(rollbackFor = OcwException.class)
	public String deactivateAddOn(AddOnDto AddOnDto) throws OcwException {
		AddOn addOn = addOnRepository.findByAddOnId(AddOnDto.getAddOnId());
		addOn.setLastRevision(OCWConstants.NO_CHAR);
		addOnRepository.save(addOn);
		return "Deactivated addOn Sucessfully";
	}

	@Transactional(rollbackFor = OcwException.class)
	public String activateAddOn(AddOnDto addOnDto) throws OcwException {
		AddOn addOn = addOnRepository.findByAddOnId(addOnDto.getAddOnId());
		addOn.setLastRevision(OCWConstants.YES_CHAR);
		addOnRepository.save(addOn);
		return "Activated addOn Sucessfully";
	}

}
