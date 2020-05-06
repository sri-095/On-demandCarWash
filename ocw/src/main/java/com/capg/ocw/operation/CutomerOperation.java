package com.capg.ocw.operation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capg.ocw.model.ReviewRatings;
import com.capg.ocw.model.dto.ReviewRatingDto;
import com.capg.ocw.repository.ReviewRatingsRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CutomerOperation {
	
	@Autowired
	private ReviewRatingsRepository reviewRatingsRepository;
	
	//Logger logger= LoggerFactory.getLogger(CutomerOperation.class);

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

}
