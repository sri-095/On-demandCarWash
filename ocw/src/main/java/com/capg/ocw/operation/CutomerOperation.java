package com.capg.ocw.operation;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.capg.ocw.model.Adderss;
import com.capg.ocw.model.CarDetails;
import com.capg.ocw.model.Customer;
import com.capg.ocw.model.PaymentDetails;
import com.capg.ocw.model.ReviewRatings;
import com.capg.ocw.model.dto.AdderssDto;
import com.capg.ocw.model.dto.CarDetailsDto;
import com.capg.ocw.model.dto.CustomerDto;
import com.capg.ocw.model.dto.PaymentDto;
import com.capg.ocw.model.dto.ReviewRatingDto;
import com.capg.ocw.repository.CarDetailsRespository;
import com.capg.ocw.repository.CustomerRepository;
import com.capg.ocw.repository.ReviewRatingsRepository;
import com.capg.ocw.util.OCWUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CutomerOperation {
	
	@Autowired
	private ReviewRatingsRepository reviewRatingsRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CarDetailsRespository carDetailsRespository;
	
	@Autowired
	private OCWUtils ocwUtils;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public List<ReviewRatingDto> viewCustomerRating(String customerid) {
		List<ReviewRatingDto> reviewRatingDto = new ArrayList<>();
		
		List<ReviewRatings> reviews = reviewRatingsRepository.findByCustomerId(customerid);
		reviews.stream().forEach(reviewsDb -> {
			ReviewRatingDto ratingDto = new ReviewRatingDto();
			ratingDto.setCustomerId(reviewsDb.getCustomerId());
			ratingDto.setWasherId(reviewsDb.getWasherId());
			ratingDto.setRatings(reviewsDb.getRatings());
			ratingDto.setReviews(reviewsDb.getReviews());
			reviewRatingDto.add(ratingDto);
		});
		log.info("Customer Ratings fetched successfully.");
		return reviewRatingDto;
	}
		
	public CustomerDto getCustomerDetails(String customerId) {
		Customer customer = customerRepository.findByCustomerId(customerId);
		List<CarDetailsDto> carDetailsDtoList = getCarDetailsForCustomer(customerId);
		AdderssDto adderssDto = getAddressDeatils(customer.getAddress());
		
		CustomerDto customerDto = new CustomerDto();
		customerDto.setAddress(adderssDto);
		customerDto.setCarDetails(carDetailsDtoList);
		customerDto.setCustomerId(customer.getCustomerId());
		customerDto.setName(customer.getName());
		customerDto.setPhoneNumber(customer.getPhoneNumber());
		customerDto.setRegistedDate(customer.getRegisteredDate());
		return customerDto;
	}

	private AdderssDto getAddressDeatils(Adderss adderss) {
		AdderssDto adderssDto = new AdderssDto();
		adderssDto.setAddressLine(adderss.getAddressLine());
		adderssDto.setCity(adderss.getCity());
		adderssDto.setZipCode(adderss.getZipCode());
		return adderssDto;
	}

	private List<CarDetailsDto> getCarDetailsForCustomer(String customerId) {
		List<CarDetails> carDetailsDb = carDetailsRespository.findByCustomerId(customerId);
		List<CarDetailsDto> carDetailsDtoList = new ArrayList<>();
		carDetailsDb.stream().forEach(carDetails -> {
			CarDetailsDto carDetailsDto = new CarDetailsDto();
			carDetailsDto.setName(carDetails.getName());
			carDetailsDto.setColor(carDetails.getColor());
			carDetailsDto.setPlateNumber(carDetails.getPlateNumber());
			carDetailsDtoList.add(carDetailsDto);
		});
		return carDetailsDtoList;
	}

	public CustomerDto updateCustomer(CustomerDto customerDto) {
		Customer customer = customerRepository.findByCustomerId(customerDto.getCustomerId());
		customer = ocwUtils.saveCustomer(customerDto);
		customerRepository.save(customer);
		return getCustomerDetails(customer.getCustomerId());
	}

	public String savePaymentDetails(PaymentDto paymentDto) {
		Customer customer = customerRepository.findByCustomerId(paymentDto.getCustomerId());
		PaymentDetails payDetails = new PaymentDetails();
		payDetails.setCardNo(paymentDto.getCardNo());
		payDetails.setPin(passwordEncoder.encode(paymentDto.getPin()));
		payDetails.setType(paymentDto.getType());
		customer.setDetails(payDetails);
		customerRepository.save(customer);
		if(paymentDto.getType().equalsIgnoreCase("cod"))
			return "Selected payment mode is cash on delivery";
		return "Paid successfully";
	}
}
