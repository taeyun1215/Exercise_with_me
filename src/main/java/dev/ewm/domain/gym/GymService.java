package dev.ewm.domain.gym;

import java.util.List;

public interface GymService {
	GymDTO register(GymDTO gymDto);
	List<GymDTO> getList();
	GymDTO getDetail(Long id) throws Exception;
	GymDTO modify(GymDTO gymDto) throws Exception;
	void modifyStarScore(Long gymId, Double avgStarScore) throws Exception;
	boolean delete(Long id);
}
