package dev.ewm.domain.gym;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Builder
@Getter
@DynamicInsert
@DynamicUpdate
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
	
	@Column(nullable = false)
	private Long userId;
	
	@Column
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime createDate;
	
	@Column
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime updateDate;
}
