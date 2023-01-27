package dev.ewm.domain.GymStar;

public interface GymStarService {
	GymStar register(GymStarDTO gymStarDto);
	GymStar myScore(GymStarDTO gymStarDto);
	Double avgScore(GymStarDTO gymStarDto);
	GymStar modify(GymStarDTO gymStarDto);
	void delete(Long id);
}
