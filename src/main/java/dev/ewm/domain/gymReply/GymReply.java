package dev.ewm.domain.gymReply;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
public class GymReply {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	@Column
	private String reply;

	@Column(nullable = false, updatable=false)
	private Long writerId;

	@Column(nullable = false, updatable=false)
	private Long GymId;
	
	@Column(updatable=false)
	@CreatedDate
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime createDate;
	
	@LastModifiedDate
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime updateDate;
	
	public GymReplyDto toDto() {
		return GymReplyDto.builder()
				.id(id)
				.reply(reply)
				.writerId(writerId)
				.GymId(GymId)
				.createDate(createDate)
				.updateDate(updateDate)
				.build();
	}
}
