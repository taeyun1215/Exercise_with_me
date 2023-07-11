package dev.ewm.domain.gymReply;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GymReplyServiceImpl implements GymReplyService {
	private final GymReplyRepo gymReplyRepo;
	
	@Override
	public GymReplyDto register(GymReplyDto gymReplyDto) {
		GymReply gymReply = gymReplyRepo.save(gymReplyDto.toEntity());
		
		return gymReply.toDto();
	}

	@Override
	public List<GymReplyDto> getList(Long gymId) {
		List<GymReplyDto> list = new ArrayList<>();
		
		gymReplyRepo.findAll().forEach(l -> list.add(l.toDto()));

		return list;
	}

	@Override
	public GymReplyDto modify(GymReplyDto gymReplyDto) {
		GymReply gymReply = gymReplyRepo.save(gymReplyDto.toEntity());
		
		return gymReply.toDto();
	}

	@Override
	public void delete(Long id) {
		gymReplyRepo.deleteById(id);
	}

}
