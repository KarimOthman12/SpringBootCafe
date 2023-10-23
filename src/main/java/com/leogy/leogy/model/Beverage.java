package com.leogy.leogy.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor()
@Setter
@Getter
public class Beverage {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	@NotNull
    @Size(max = 50)
	private String name;
	@NotNull
	private double cost;
	@NotNull
    @Size(max = 10)
	private String cupSize;
	private String note;
	//@OneToMany(mappedBy="beverage", cascade=CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
	//private List<Review> reviews = new ArrayList<Review>();
	


	
}
