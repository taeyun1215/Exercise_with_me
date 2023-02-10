package dev.ewm.domain.GymStar;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

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
	
	@Column(nullable = false, updatable=false)
	private Long userId;
	
	@Column(nullable = false, updatable=false)
	private Long gymId;
	
	@Column(updatable=false)
	@CreatedDate
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime createDate;
	
	@LastModifiedDate
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime updateDate;
}
