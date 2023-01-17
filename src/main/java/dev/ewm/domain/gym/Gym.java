package dev.ewm.domain.gym;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Gym {
	@Id
	@GeneratedValue(strategy = IDENTITY)
    private Long id;
	
	@Column(nullable = false, unique = true)
    private String gymName;
	
	@Column(nullable = false)
	private String address;
	
	@Column
	private Integer priceOfMonth;
	
	@Column
	private Double countingStar;
	
	@Column(nullable = false)
	private Long userId;
}
