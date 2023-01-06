package dev.ewm.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepo extends JpaRepository<User, Long> {
}
