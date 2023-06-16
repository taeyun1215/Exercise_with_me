package dev.ewm.domain.gymReply;

import java.time.LocalDateTime;

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
public class GymReplyDto {
	@Min(1)
	private Long id;

	private String reply;

	@Min(1)
	private Long writerId;

	@Min(1)
	private Long GymId;

	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
	public GymReply toEntity() {
		return GymReply.builder()
				.id(id)
				.reply(reply)
				.writerId(writerId)
				.GymId(GymId)
				.createDate(createDate)
				.updateDate(updateDate)
				.build();
	}
}
