package dev.ewm.domain.GymStar;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.ewm.domain.gym.GymService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GymStarServiceImpl implements GymStarService {
	private final GymStarRepo gymStarRepo;
	private final GymService gymService;
	
	//modify와 합칠 수 있는지 확인
	@Override
	@Transactional
	public GymStar register(GymStarDTO gymStarDto) {
		GymStar gymStar = gymStarRepo.save(gymStarDto.toEntity());
		
		gymService.modifyStarScore(gymStar.getGymId(), avgScore(gymStar.getGymId()));
		
		return gymStar;
	}

	@Override
	@Transactional
	public GymStar myScore(GymStarDTO gymStarDto) {
		GymStar gymStar = gymStarRepo.findByUserIdAndGymId(gymStarDto.getUserId(), gymStarDto.getGymId());
		
		return gymStar;
	}

	@Override
	@Transactional
	public Double avgScore(Long gymId) {
		List<GymStar> list = gymStarRepo.findAllByGymId(gymId);
		
		Double avg = 0.0;
		
		//stream 사용해보기
		for(GymStar l : list) {
			avg += l.getScore();
		}
		
		return avg/list.size();
	}

	@Override
	@Transactional
	public GymStar modify(GymStarDTO gymStarDto) {
		GymStar gymStar = gymStarRepo.save(gymStarDto.toEntity());

		gymService.modifyStarScore(gymStar.getGymId(), avgScore(gymStar.getGymId()));
		
		return gymStar;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		gymStarRepo.deleteById(id);
	}

}
