package com.leogy.leogy.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leogy.leogy.model.Beverage;
import com.leogy.leogy.model.Review;
import com.leogy.leogy.reposiory.BeverageRepository;
import com.leogy.leogy.reposiory.ReviewRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private BeverageRepository beverageRepository;
	
	public List<Review> getReviewsForBeverage(long id) {
		return reviewRepository.findByBeverageId(id);
	}
	
	public void addReviewForBeverage(Long beverageId, Review review) {
		Optional<Beverage> beverageOptional  = beverageRepository.findById(beverageId);
	    if (beverageOptional .isPresent()) {
	        Beverage beverage = beverageOptional.get();
			review.setBeverage(beverage);
	    }   
	    else {
	        throw new EntityNotFoundException("Beverage not found with ID: " + beverageId);
	    }
		reviewRepository.save(review);
	}
	
	public List<Review> getAllReviews() {
		return reviewRepository.findAll();
	}
}
