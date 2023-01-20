package dev.ewm.domain.gym;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

//@Repository
public interface GymRepo extends JpaRepository<Gym, Long>{
//	List<Gym> findAll();
	Gym findByUserName(String userName);
}
