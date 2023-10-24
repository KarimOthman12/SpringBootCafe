package com.leogy.leogy.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leogy.leogy.model.Beverage;
import com.leogy.leogy.model.Review;
import com.leogy.leogy.reposiory.BeverageRepository;
import com.leogy.leogy.reposiory.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private BeverageRepository beverageRepository;
	
	public Optional<List<Review>> getReviewsForBeverage(long id) {
		return reviewRepository.findByBeverageId(id);
	}
	
	public Optional<Beverage> addReviewForBeverage(Long beverageId, Review review) {
		Optional<Beverage> beverageOptional  = beverageRepository.findById(beverageId);
	    if (beverageOptional.isEmpty()) {
	        return beverageOptional;
	    }   
	    else {
	    	Beverage beverage = beverageOptional.get();
	    	review.setBeverage(beverage);
			reviewRepository.save(review);
	        return beverageOptional;
	    }
	}
	
	public List<Review> getAllReviews() {
		return reviewRepository.findAll();
	}
}
