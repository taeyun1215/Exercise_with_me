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
		gymDto.setCreateDate(LocalDateTime.now());
		log.info("star: {}", gymDto.getCountingStar());
		Gym gymd = gymDto.toEntity();
		log.info("gym star: {}", gymd.getCountingStar());
		Gym gym = gymRepo.save(gymd);
		
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
	public Gym modify(GymDTO gymDto) {
		gymDto.setUpdateDate(LocalDateTime.now());
		
		return gymRepo.save(gymDto.gymUpdate());
	}

	@Override
	public void delete(Long id) {
		gymRepo.deleteById(id);
	}

}
