package dev.ewm.domain.gym;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

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
	
//	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
//	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime createDate;
	
//	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
//	@JsonSerialize(using = LocalDateTimeSerializer.class)
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
}
