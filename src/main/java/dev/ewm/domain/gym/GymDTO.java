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
}
