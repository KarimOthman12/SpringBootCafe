package com.leogy.leogy.reposiory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leogy.leogy.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	Optional<List<Review>> findByBeverageId(long id);
	void deleteAllReviewsByBeverageId(long id);
}
