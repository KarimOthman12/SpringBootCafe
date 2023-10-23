package com.leogy.leogy.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leogy.leogy.model.Beverage;
import com.leogy.leogy.reposiory.BeverageRepository;

import jakarta.transaction.Transactional;

@Service
public class BeverageService {
	@Autowired
	private BeverageRepository beverageRepository;

	
	public List<Beverage> getBeverages() {
		return beverageRepository.findAll();
	}
	
	public void addNewBeverage(Beverage beverage) {
		Optional<Beverage> optionalBeverageByName =
				beverageRepository.findBeverageByName(beverage.getName());
		if(optionalBeverageByName.isPresent()) {
			throw new IllegalStateException("name is taken, please try different name");
		}
		beverageRepository.save(beverage);
	}
	
    @Transactional
	public void deleteBeverageByName(String beverageName) {
    	Optional<Beverage> optionalBeverageByName =
				beverageRepository.findBeverageByName(beverageName);
		if(!optionalBeverageByName.isPresent()) {
			throw new IllegalStateException("no such beverage");
		}
        beverageRepository.deleteBeverageByName(beverageName);
	}
    
    @Transactional
    public void updateCost(String beverageName, double cost ) {
    	Optional<Beverage> optionalBeverageByName =
				beverageRepository.findBeverageByName(beverageName);
		if(!optionalBeverageByName.isPresent()) {
			throw new IllegalStateException("no such beverage");
		}
		Beverage beverage = optionalBeverageByName.get();
		beverage.setCost(cost);
    }
	
}
