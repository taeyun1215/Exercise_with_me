package dev.ewm.domain.GymStar;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class GymStarDTO {
    private Long id;
    
    @Min(0) @Max(5)
	private Double score;
    
    @Min(1)
	private Long userId;
    
    @Min(1)
	private Long gymId;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
	public GymStar toEntity() {
		return GymStar.builder()
				.id(id)
				.score(score)
				.userId(userId)
				.gymId(gymId)
				.build();
	}
}
