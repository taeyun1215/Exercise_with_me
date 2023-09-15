package unitTest.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import user.adapter.out.persistence.UserJpaEntity;
import user.adapter.out.persistence.UserJpaRepo;
import user.adapter.out.persistence.UserPersistenceAdapter;
import user.adapter.out.persistence.UserPersistenceMapper;
import user.domain.User;
import user.domain.constant.Role;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserPersistenceAdapter 단위 테스트")
class UserPersistenceAdapterTest {

    @Mock
    private UserJpaRepo userJpaRepo;

    @Mock
    private UserPersistenceMapper userPersistenceMapper;

    @InjectMocks
    private UserPersistenceAdapter userPersistenceAdapter;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .userId(1L)
                .username("testuser")
                .password("testpassword")
                .nickname("testnickname")
                .phone("123456789")
                .email("test@test.com")
                .role(Role.USER)
                .build();
    }

    @Test
    @DisplayName("유저 저장 시 JpaRepo save 메소드가 올바르게 호출되는지 검증")
    void testSaveUser() {
        // given
        UserJpaEntity userJpaEntity = new UserJpaEntity();
        when(userPersistenceMapper.mapToJpaEntity(any(User.class))).thenReturn(userJpaEntity);

        // when
        userPersistenceAdapter.saveUser(user);

        // then
        verify(userJpaRepo, times(1)).save(userJpaEntity);
    }

    @Test
    @DisplayName("유저명으로 검색 시 반환 결과가 올바른지 검증")
    void testFindByUsername() {
        // given
        UserJpaEntity userJpaEntity = new UserJpaEntity();
        when(userJpaRepo.findByUsername(anyString())).thenReturn(Optional.of(userJpaEntity));
        when(userPersistenceMapper.mapToDomainEntity(any(UserJpaEntity.class))).thenReturn(user);

        // when
        User result = userPersistenceAdapter.findByUsername("testuser");

        // then
        assertEquals(user, result);
    }

    @Test
    @DisplayName("닉네임으로 검색 시 반환 결과가 올바른지 검증")
    void testFindByNickname() {
        // given
        UserJpaEntity userJpaEntity = new UserJpaEntity();
        when(userJpaRepo.findByUsername(anyString())).thenReturn(Optional.of(userJpaEntity));
        when(userPersistenceMapper.mapToDomainEntity(any(UserJpaEntity.class))).thenReturn(user);

        // when
        User result = userPersistenceAdapter.findByNickname("testnickname");

        // then
        assertEquals(user, result);
    }

    @Test
    @DisplayName("유저명 업데이트 시 JpaRepo save 메소드가 올바르게 호출되는지 검증")
    void testUpdateUsername() {
        // given
        UserJpaEntity userJpaEntity = new UserJpaEntity();
        UserJpaEntity newUserJpaEntity = new UserJpaEntity();

        when(userJpaRepo.findById(anyLong())).thenReturn(Optional.of(userJpaEntity));
        when(userPersistenceMapper.mapToDomainEntity(any(UserJpaEntity.class))).thenReturn(user);
        when(userPersistenceMapper.mapToJpaEntity(any(User.class))).thenReturn(newUserJpaEntity);

        // when
        userPersistenceAdapter.updateUsername(user, "newUsername");

        // then
        verify(userJpaRepo, times(1)).save(any(UserJpaEntity.class));
    }

    @Test
    @DisplayName("존재하지 않는 유저의 유저명 업데이트 시 EntityNotFoundException이 발생하는지 검증")
    void testUpdateUsernameEntityNotFound() {
        when(userJpaRepo.findById(anyLong())).thenReturn(Optional.empty());

        // when
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            userPersistenceAdapter.updateUsername(user, "newUsername");
        });

        // then
        assertTrue(exception instanceof EntityNotFoundException);
    }
}
