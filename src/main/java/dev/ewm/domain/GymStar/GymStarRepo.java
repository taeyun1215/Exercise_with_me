package dev.ewm.domain.gymStar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GymStarRepo extends JpaRepository<GymStar, Long>{
	GymStar findByUserIdAndGymId(Long userId, Long gymId);
	List<GymStar> findAllByGymId(Long gymId);
}
