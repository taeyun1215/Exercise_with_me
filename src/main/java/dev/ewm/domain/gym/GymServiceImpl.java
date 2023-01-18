package dev.ewm.domain.gym;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GymServiceImpl implements GymService {
	private final GymRepo gymRepo;
	
	@Override
	@Transactional
	public void register(GymDTO gymDto) {
		Gym gym = gymDto.toEntity();
		gymRepo.save(gymDto);
	}

}
