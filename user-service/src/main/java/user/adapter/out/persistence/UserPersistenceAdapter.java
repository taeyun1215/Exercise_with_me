package user.adapter.out.persistence;

import global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import user.application.port.out.LoadUserPort;
import user.application.port.out.SaveUserPort;
import user.application.port.out.UpdateUserStatePort;
import user.domain.User;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@PersistenceAdapter
public class UserPersistenceAdapter
        implements SaveUserPort, LoadUserPort, UpdateUserStatePort {

    private final UserJpaRepo userJpaRepo;
    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    @Transactional
    public void saveUser(User user) {
        userJpaRepo.save(userPersistenceMapper.mapToJpaEntity(user));
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userJpaRepo.findByUsername(username)
                .map(userPersistenceMapper::mapToDomainEntity)
                .orElse(null);
    }

    @Override
    @Transactional
    public User findByNickname(String nickname) {
        return userJpaRepo.findByUsername(nickname)
                .map(userPersistenceMapper::mapToDomainEntity)
                .orElse(null);
    }

    @Override
    @Transactional
    public void updateUsername(User user, String username) {
        UserJpaEntity findUserJpaEntity = userJpaRepo.findById(user.getUserId()).orElseThrow(
                EntityNotFoundException::new
        );

        User saveUser = userPersistenceMapper.mapToDomainEntity(findUserJpaEntity);
        saveUser.updateUsername(username);

        userJpaRepo.save(userPersistenceMapper.mapToJpaEntity(saveUser));
    }

}

