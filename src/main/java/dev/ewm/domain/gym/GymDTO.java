package dev.ewm.domain.gym;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GymDTO {
    private Long id;
    private String gymName;
	private String address;
	private Integer priceOfMonth;
	private Double countingStar;
	private Long userId;
	private String createDate;
	private String updateDate;
	
	public Gym toEntity() {
		return Gym.builder()
				.id(id)
				.gymName(gymName)
				.address(address)
				.priceOfMonth(priceOfMonth)
				.countingStar(countingStar)
				.userId(userId)
				.build();
	}
	
//	public GymDTO toGymDto(Gym gym) {
//		return GymDTO.builder()
//				.id(gym.getId())
//				.gymName(gym.getGymName())
//				.address(gym.getAddress())
//				.priceOfMonth(gym.getPriceOfMonth())
//				.countingStar(gym.getCountingStar())
//				.userId(gym.getUserId())
//				.build();
//	}
}
