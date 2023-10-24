package com.leogy.leogy.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.leogy.leogy.DTO.Created;
import com.leogy.leogy.DTO.ErrorResponse;
import com.leogy.leogy.DTO.Message;
import com.leogy.leogy.Service.BeverageService;
import com.leogy.leogy.model.Beverage;

@RestController
@RequestMapping(path="api/beverage")
public class BeverageController {
	@Autowired
	private BeverageService beverageService;	

	@GetMapping
	public ResponseEntity<List<Beverage>> getBeverages(){
		 return new ResponseEntity<>(beverageService.getBeverage(),HttpStatus.OK);
		 }

	@PostMapping
	public ResponseEntity<Object> addNewBeverage(@RequestBody Beverage beverage) {
		Optional<Beverage> OptionalBeverage = beverageService.findBeverageByName(beverage.getName());
		if(OptionalBeverage.isPresent()) {
			return new ResponseEntity<>(new ErrorResponse("name is already taken"),HttpStatus.NOT_ACCEPTABLE);
		} else
		return new ResponseEntity<>(new Created(beverageService.addNewBeverage(beverage).getId()),HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{beverageName}")
	public ResponseEntity< Object> deleteBeveragebyName(@PathVariable("beverageName")String name) {
		Optional<Beverage> beverage= beverageService.findBeverageByName(name);
	       if(beverage.isPresent()){
	    	   beverageService.deleteBeverageByName(name);
	            return new ResponseEntity<>(new Message("beverage has been deleted"),HttpStatus.NO_CONTENT);
	        }else{
	           return new ResponseEntity<>(new ErrorResponse("not such beverage"),HttpStatus.NOT_FOUND);
	        }
	}
	
	@PutMapping(path="{beverageName}")
	public ResponseEntity< Object> updateBeverageCost(
			@PathVariable("beverageName") String name,
			@RequestParam double cost) {
		Optional<Beverage> beverage= beverageService.updateCost(name, cost);
        if(beverage.isPresent()){
            return new ResponseEntity<>(beverage.get(),HttpStatus.OK);
        }else{
           return new ResponseEntity<>(new ErrorResponse("not such beverage"),HttpStatus.NOT_FOUND);
        }
	}
	//todo create response interface instead of object
	@GetMapping(path="{beverageName}")
	public ResponseEntity< Object> getBeverageByName(@PathVariable("beverageName") String name){
		Optional<Beverage> beverage= beverageService.findBeverageByName(name);
        if(beverage.isPresent()){
            return new ResponseEntity<>(beverage.get(),HttpStatus.OK);
        }else{
           return new ResponseEntity<>(new ErrorResponse("no such beverage"),HttpStatus.NOT_FOUND);
        }
	}
}
