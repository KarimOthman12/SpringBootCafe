package com.leogy.leogy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Review {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
    @Min(1)
    @Max(5)
	private int stars;
	@NotNull
    @Size(max = 50)
	private String title;
	private String content;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="beverage_id")
	private Beverage beverage;
	
}
