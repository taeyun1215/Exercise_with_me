//package unitTest;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import user.adapter.out.persistence.UserJpaEntity;
//import user.adapter.out.persistence.UserJpaRepo;
//import user.adapter.out.persistence.UserPersistenceAdapter;
//import user.adapter.out.persistence.UserPersistenceMapper;
//import user.domain.User;
//import user.domain.constant.Role;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class UserPersistenceAdapterTest {
//
//    private UserJpaRepo userJpaRepo;
//    private UserPersistenceMapper userPersistenceMapper;
//    private UserPersistenceAdapter userPersistenceAdapter;
//
//    private User user;
//
//    @BeforeEach
//    void setUp() {
//        userJpaRepo = new UserJpaRepoFake(); // Fake repository 구현체 사용
//        userPersistenceMapper = new UserPersistenceMapper();
//        userPersistenceAdapter = new UserPersistenceAdapter(userJpaRepo, userPersistenceMapper);
//
//        user = User.builder()
//                .userId(1L)
//                .username("testuser")
//                .password("testpassword")
//                .nickname("testnickname")
//                .phone("123456789")
//                .email("test@test.com")
//                .role(Role.USER)
//                .build();
//    }
//
//    @Test
//    @DisplayName("유저 저장 시 JpaRepo save 메소드가 올바르게 호출되는지 검증")
//    void testSaveUser() {
//        // given
//        UserJpaEntity userJpaEntity = userPersistenceMapper.mapToJpaEntity(user);
//
//        // when
//        userPersistenceAdapter.saveUser(user);
//
//        // then
//        UserJpaEntity savedEntity = userJpaRepo.findById(1L).orElse(null);
//        assertEquals(userJpaEntity, savedEntity);
//    }
//}