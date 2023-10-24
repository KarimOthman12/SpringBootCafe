package com.leogy.leogy.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leogy.leogy.DTO.ErrorResponse;
import com.leogy.leogy.Service.BeverageService;
import com.leogy.leogy.Service.ReviewService;
import com.leogy.leogy.model.Review;

@RestController
@RequestMapping(path="api/beverage")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private BeverageService beverageService;
	
	@GetMapping(path="/{beverageId}/reviews")
	public ResponseEntity<Object> getReviewsForBeverage(@PathVariable("beverageId")Long id){
		if(beverageService.findBeverageById(id).isPresent()) {
			Optional<List<Review>> optionalReviews = reviewService.getReviewsForBeverage(id);
			if(optionalReviews.isPresent() && !optionalReviews.get().isEmpty()) {
		        List<Review> reviews = optionalReviews.orElseThrow();
	        	return new ResponseEntity<>(reviews, HttpStatus.OK);
			} else {
		        return new ResponseEntity<>(new ErrorResponse("this beverage has no reviews"),HttpStatus.BAD_REQUEST);
			}
		}else
	        return new ResponseEntity<>(new ErrorResponse("no such beverage"),HttpStatus.NOT_FOUND);

	}
	
	@PostMapping(path="/{beverageId}")
	public ResponseEntity<Object> addNewBeverageReview(@PathVariable("beverageId") Long id, @RequestBody Review review) {
		if(beverageService.findBeverageById(id).isPresent()) {
			reviewService.addReviewForBeverage( id, review).get();
			return new ResponseEntity<>(review, HttpStatus.CREATED);
		}
		else
	        return new ResponseEntity<>(new ErrorResponse("no such beverage"),HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path="/reviews")
	public List<Review> getAllReviews(){
		return reviewService.getAllReviews();
	}
}
