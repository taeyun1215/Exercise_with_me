package dev.ewm.domain.GymStar;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.ewm.domain.gym.Gym;
import dev.ewm.domain.gym.GymDTO;
import dev.ewm.domain.gym.GymService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GymStarServiceImpl implements GymStarService {
	private final GymStarRepo gymStarRepo;
	private final GymService gymService;
	
	@Override
	public GymStar register(GymStarDTO gymStarDto) {
		gymStarDto.setCreateDate(LocalDateTime.now());
		GymStar gymStar = gymStarRepo.save(gymStarDto.toEntity());
		
		return gymStar;
	}

	@Override
	public GymStar myScore(GymStarDTO gymStarDto) {
		GymStar gymStar = gymStarRepo.findByUserIdAndGymId(gymStarDto.getUserId(), gymStarDto.getGymId());
		
		return gymStar;
	}

	@Override
	public Double avgScore(GymStarDTO gymStarDto) {
		List<GymStar> list = gymStarRepo.findAllByGymId(gymStarDto.getGymId());
		
		Double avg = 0.0;
		
		for(GymStar l : list) {
			avg += l.getScore();
		}
		
		return avg/list.size();
	}

	@Override
	public GymStar modify(GymStarDTO gymStarDto) {
		gymStarDto.setUpdateDate(LocalDateTime.now());
		GymStar gymStar = gymStarRepo.save(gymStarDto.toEntity());
		
		Gym gym = gymService.getDetail(gymStarDto.getGymId());
		gym.builder().countingStar(avgScore())
		
		return gymStar;
	}

	@Override
	public void delete(Long id) {
		gymStarRepo.deleteById(id);
	}

}
