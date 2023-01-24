package dev.ewm.domain.gym;

import java.util.List;

public interface GymService {
	Gym register(GymDTO gymDto);
	List<Gym> getList();
	Gym getDetail(Long userId);
	Gym modify(GymDTO gymDto);
	void delete(Long id);
}
