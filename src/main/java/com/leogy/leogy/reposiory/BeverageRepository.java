package com.leogy.leogy.reposiory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leogy.leogy.model.Beverage;

@Repository
public interface BeverageRepository extends JpaRepository<Beverage, Long> {
	Optional<Beverage> findBeverageByName(String name);
	Optional<String> deleteBeverageByName(String name);	
}
