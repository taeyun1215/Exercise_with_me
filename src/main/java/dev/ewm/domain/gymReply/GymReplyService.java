package dev.ewm.domain.gymReply;

import java.util.List;

public interface GymReplyService {
	GymReplyDto register(GymReplyDto gymReplyDto);
	List<GymReplyDto> getList(Long gymId);
	GymReplyDto modify(GymReplyDto gymReplyDto);
	void delete(Long id);
}
