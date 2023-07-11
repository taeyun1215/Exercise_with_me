package user.adapter.out.persistence;

import org.springframework.stereotype.Component;
import user.domain.User;

@Component
public class UserPersistenceMapper {

    public User mapToDomainEntity(UserJpaEntity userJpaEntity) {

        return User.builder()
                 .userId(userJpaEntity.getId())
                 .username(userJpaEntity.getUsername())
                 .password(userJpaEntity.getPassword())
                 .nickname(userJpaEntity.getNickname())
                 .phone(userJpaEntity.getPhone())
                 .email(userJpaEntity.getEmail())
                 .role(userJpaEntity.getRole())
                 .build();
    }

    public UserJpaEntity mapToJpaEntity(User user) {
        return user.toJpaEntity();
    }

}
