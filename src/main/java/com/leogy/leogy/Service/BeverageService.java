package com.leogy.leogy.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leogy.leogy.model.Beverage;
import com.leogy.leogy.reposiory.BeverageRepository;
import com.leogy.leogy.reposiory.ReviewRepository;

import jakarta.transaction.Transactional;

@Service
public class BeverageService {
	@Autowired
	private BeverageRepository beverageRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	public List<Beverage> getBeverage() {
		return beverageRepository.findAll();
	}
	
	public Beverage addNewBeverage(Beverage beverage) {
		return beverageRepository.save(beverage);
	}	

    @Transactional
	public Optional<Beverage> deleteBeverageByName(String beverageName) {
    	Optional<Beverage> optionalBeverageByName =
				beverageRepository.findBeverageByName(beverageName);
    	if(optionalBeverageByName.isPresent()) {
    		Beverage beverage = optionalBeverageByName.get();
    		beverage.getId();
    		reviewRepository.deleteAllReviewsByBeverageId(beverage.getId());
    	}
    	beverageRepository.deleteBeverageByName(beverageName);
    	return optionalBeverageByName;
	}
    
    @Transactional
    public Optional<Beverage> updateCost(String beverageName, double cost ) {
    	Optional<Beverage> optionalBeverageByName =
				beverageRepository.findBeverageByName(beverageName);
		if(optionalBeverageByName.isEmpty()) {
			return optionalBeverageByName;
		}
		Beverage beverage = optionalBeverageByName.get();
		beverage.setCost(cost);
		return Optional.of(beverage);
    }
    
    public Optional<Beverage> findBeverageByName(String beverageName) {
    	return beverageRepository.findBeverageByName(beverageName);
    }
    
    public Optional<Beverage> findBeverageById(Long id) {
    	return beverageRepository.findById(id);
    }
	
}
