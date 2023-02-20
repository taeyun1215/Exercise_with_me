package dev.ewm.domain.gym;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GymServiceImpl implements GymService {
	private final GymRepo gymRepo;
	
	@Override
	@Transactional
	public Gym register(GymDTO gymDto) {
		Gym gym = gymRepo.save(gymDto.toEntity());
		
		return gym;
	}

	@Override
	@Transactional
	public List<Gym> getList() {
		List<Gym> list = gymRepo.findAll();

		return list;
	}

	@Override
	@Transactional
	public Gym getDetail(Long id) {
		Optional<Gym> optional = gymRepo.findById(id);
		
		Gym gym = optional.get();
		
		return gym;
	}

	@Override
	@Transactional
	public Gym modify(GymDTO gymDto) {
		gymDto.setUpdateDate(LocalDateTime.now());
		
		return gymRepo.save(gymDto.toEntity());
	}

	@Override
	@Transactional
	public void modifyStarScore(Long gymId, Double avgStarScore) {
		GymDTO gymDto = getDetail(gymId).toDto();
		gymDto.setCountingStar(avgStarScore);
		modify(gymDto);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		//예외 처리를 위한 try-catch고려
		gymRepo.deleteById(id);
		log.info("{} 삭제 완료", id);
	}

}
