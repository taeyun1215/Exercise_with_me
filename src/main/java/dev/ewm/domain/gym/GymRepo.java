package dev.ewm.domain.gym;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

//@Repository
public interface GymRepo extends JpaRepository<Gym, Long>{
	Gym findByUserId(Long userId);
}
