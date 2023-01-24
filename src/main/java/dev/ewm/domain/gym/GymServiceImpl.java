package dev.ewm.domain.gym;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GymServiceImpl implements GymService {
	private final GymRepo gymRepo;
	
	@Override
	@Transactional
	public Gym register(GymDTO gymDto) {
		return gymRepo.save(gymDto.toEntity());
	}

	@Override
	@Transactional
	public List<Gym> getList() {
		List<Gym> list = gymRepo.findAll();

		return list;
	}

	@Override
	@Transactional
	public Gym getDetail(Long userId) {
		Gym gym = gymRepo.findByUserId(userId);
		
		return gym;
	}

	@Override
	public Gym modify(GymDTO gymDto) {
		Gym gym = gymRepo.save(gymDto.toEntity());

		return gym;
	}

	@Override
	public void delete(Long id) {
		gymRepo.deleteById(id);
	}

}
