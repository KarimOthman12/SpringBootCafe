package com.leogy.leogy.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leogy.leogy.Service.ReviewService;
import com.leogy.leogy.model.Review;

@RestController
@RequestMapping(path="api/beverage")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping(path="/{beverageId}/reviews")
	public List<Review> getReviewsForBeverage(@PathVariable("beverageId")Long id){
		return reviewService.getReviewsForBeverage(id);
	}
	
	@PostMapping(path="/{beverageId}")
	public void addNewBeverageReview(@PathVariable("beverageId") Long id, @RequestBody Review review) {
		reviewService.addReviewForBeverage( id, review);
	}
	
	@GetMapping(path="/reviews")
	public List<Review> getAllReviews(){
		return reviewService.getAllReviews();
	}
}
