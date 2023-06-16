package dev.ewm.domain.gymStar;

public interface GymStarService {
	GymStar register(GymStarDTO gymStarDto);
	GymStar myScore(GymStarDTO gymStarDto);
	Double avgScore(Long gymId);
	GymStar modify(GymStarDTO gymStarDto);
	void delete(Long id);
}
