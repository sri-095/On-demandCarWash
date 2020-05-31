package com.capg.ocw.operation;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.capg.ocw.exception.OcwException;
import com.capg.ocw.model.Customer;
import com.capg.ocw.model.User;
import com.capg.ocw.model.dto.CustomerDto;
import com.capg.ocw.model.dto.UserDto;
import com.capg.ocw.repository.AddressRepository;
import com.capg.ocw.repository.CarDetailsRespository;
import com.capg.ocw.repository.CustomerRepository;
import com.capg.ocw.repository.UserRepository;
import com.capg.ocw.util.OCWUtils;

@Component
public class UserLoginOperation {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CarDetailsRespository carDetailsRespository;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private OCWUtils ocwUtils;
	
	@Transactional(rollbackFor = OcwException.class)
	public String saveUser(CustomerDto customerDto) throws OcwException {
		String msg =null;
			User user = new User();
			user.setUserId(customerDto.getCustomerUser().getUserId());
			user.setPassword(passwordEncoder.encode(customerDto.getCustomerUser().getPassword()));
			user.setRoles(customerDto.getCustomerUser().getRoles());
			userRepository.save(user);
			
			Customer customer = ocwUtils.saveCustomer(customerDto);
			String customerId = ocwUtils.prepareId(customerRepository.findAll().size(), "C");
			customer.setCustomerUser(user);
			customer.setRegisteredDate(new Date());
			customer.setCustomerId(customerId);
			customerDto.getAddress().setCustomerId(customerId);
			customerDto.getCarDetails().stream().forEach(car -> car.setCustomerId(customerId));
			customer.setAddress(ocwUtils.saveAddress(customerDto.getAddress()));
			customer.setCarDetails(ocwUtils.saveCarDetails(customerDto.getCarDetails()));
			customerRepository.save(customer);
			carDetailsRespository.saveAll(customer.getCarDetails());
			addressRepository.save(customer.getAddress());
			msg = "Saved user successfully. Your customer ID is " + customerId;
		return msg;
			
	}
	public String validateUser(UserDto userDto) throws OcwException {
		String msg =null;
		User user = userRepository.findByUserId(userDto.getUserId());
		if(null == user) {
			throw new OcwException("User Not Authenticated");
		}
		else {
			if(user.getUserId().equals(userDto.getUserId()) && passwordEncoder.matches(userDto.getPassword() , user.getPassword()))
				msg = "User Authenticated";
			return msg;
		}
	}
	
	

}
