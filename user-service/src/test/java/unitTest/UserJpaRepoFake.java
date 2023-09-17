package unitTest;

import user.adapter.out.persistence.UserJpaEntity;
import user.adapter.out.persistence.UserJpaRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class UserJpaRepoFake implements UserJpaRepo {
    private final Map<Long, UserJpaEntity> db = new HashMap<>();

    @Override
    public UserJpaEntity save(UserJpaEntity entity) {
        db.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<UserJpaEntity> findAll() {
        throw new UnsupportedOperationException("Not implemented for testing.");
    }

    @Override
    public Optional<UserJpaEntity> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public Optional<UserJpaEntity> findByUsername(String username) {
        return db.values().stream()
                .filter(entity -> entity.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public Optional<UserJpaEntity> findByNickname(String nickname) {
        return db.values().stream()
                .filter(entity -> entity.getNickname().equals(nickname))
                .findFirst();
    }

}
