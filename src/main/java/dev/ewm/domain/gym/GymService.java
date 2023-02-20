package dev.ewm.domain.gym;

import java.util.List;

public interface GymService {
	Gym register(GymDTO gymDto);
	List<Gym> getList();
	Gym getDetail(Long id);
	Gym modify(GymDTO gymDto);
	void modifyStarScore(Long gymId, Double avgStarScore);
	void delete(Long id);
}
