package dev.ewm.domain.gym;

import java.util.List;

public interface GymService {
	void register(GymDTO gymDto);
	List<Gym> getList();
	Gym getDetail(String userName);
}
