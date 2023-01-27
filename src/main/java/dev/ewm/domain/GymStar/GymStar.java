package dev.ewm.domain.GymStar;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class GymStar {
	@Id
	@GeneratedValue(strategy = IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private Double score;
	
	@Column(nullable = false)
	private Long userId;
	
	@Column(nullable = false)
	private Long gymId;
	
	@Column
	private LocalDateTime createDate;
	
	@Column
	private LocalDateTime updateDate;
}
