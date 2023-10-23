package com.leogy.leogy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leogy.leogy.Service.BeverageService;
import com.leogy.leogy.model.Beverage;

@RestController
@RequestMapping(path="api/beverage")
public class BeverageController {
	@Autowired
	private BeverageService beverageService;
	
	@GetMapping
	public List<Beverage> getBeverage(){
		return beverageService.getBeverages();
	}

	@PostMapping
	public void addNewBeverage(@RequestBody Beverage beverage) {
		beverageService.addNewBeverage(beverage);
	}
	
	//consider spaces like abdul%20karim
	@DeleteMapping(path="/{beverageName}")
	public void deleteBeveragebyName(@PathVariable("beverageName")String name) {
		beverageService.deleteBeverageByName(name);
	}
	
	//afterpath?cost=44
	@PutMapping(path="{beverageName}")
	public void updateBeverageCost(
			@PathVariable("beverageName") String name,
			@RequestParam double cost) {
		beverageService.updateCost(name, cost);
	}
}
