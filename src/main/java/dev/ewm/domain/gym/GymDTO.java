package dev.ewm.domain.gym;

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
public class GymDTO {
	@Min(1)
    private Long id;
	
    private String gymName;
	private String address;
	
	@Min(0)
	private Integer priceOfMonth;
	
	@Min(0) @Max(5)
	private Double countingStar;
	
	@Min(1)
	private Long userId;
	
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
	public Gym toEntity() {
		return Gym.builder()
				.id(id)
				.gymName(gymName)
				.address(address)
				.priceOfMonth(priceOfMonth)
				.countingStar(countingStar)
				.userId(userId)
				.createDate(createDate)
				.updateDate(updateDate)
				.build();
	}
	
	public Gym gymUpdate() {
		return Gym.builder()
				.id(id)
				.gymName(gymName)
				.address(address)
				.priceOfMonth(priceOfMonth)
				.countingStar(countingStar)
				.userId(userId)
				.createDate(createDate)
				.updateDate(updateDate)
				.build();
	}
}
