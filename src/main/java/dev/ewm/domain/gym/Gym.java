package dev.ewm.domain.gym;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Gym {
	@Id
	@GeneratedValue(strategy = IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String gymName;
	
	@Column(nullable = false)
	private String address;
	
	@Column
	private Integer priceOfMonth;
	
	@Column
	@ColumnDefault("0")
	private Double countingStar;
	
	//@Column
	//private String description;
	
	@Column(nullable = false, updatable=false)
	private Long userId;
	
	@Column(updatable=false)
	@CreatedDate
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime createDate;
	
	@LastModifiedDate
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime updateDate;
	
	public GymDTO toDto() {
		return GymDTO.builder()
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
