package unitTest.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.adapter.out.persistence.UserJpaEntity;
import user.domain.User;
import user.domain.constant.Role;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User Domain 단위 테스트")
class UserTest {

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
    @DisplayName("유저 도메인 엔티티 빌드 테스트")
    void testBuilder() {
        assertNotNull(user);
        assertEquals(1L, user.getUserId());
        assertEquals("testuser", user.getUsername());
        assertEquals("testpassword", user.getPassword());
        assertEquals("testnickname", user.getNickname());
        assertEquals("123456789", user.getPhone());
        assertEquals("test@test.com", user.getEmail());
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    @DisplayName("유저 도메인 엔티티 jpa 엔티티 변환 테스트")
    void testToJpaEntity() {
        UserJpaEntity userJpaEntity = user.toJpaEntity();

        assertNotNull(userJpaEntity);
        assertEquals(1L, userJpaEntity.getId());
        assertEquals("testuser", userJpaEntity.getUsername());
        assertEquals("testpassword", userJpaEntity.getPassword());
        assertEquals("testnickname", userJpaEntity.getNickname());
        assertEquals("123456789", userJpaEntity.getPhone());
        assertEquals("test@test.com", userJpaEntity.getEmail());
        assertEquals(Role.USER, userJpaEntity.getRole());
    }

    @Test
    @DisplayName("유저 도메인 엔티티 권한 반환 테스트")
    void testGetRoleValue() {
        assertEquals(Role.USER.getValue(), user.getRoleValue());
    }

    @Test
    @DisplayName("유저 도메인 엔티티 유저이름 업데이트 테스트")
    void testUpdateUsername() {
        user.updateUsername("newUsername");
        assertEquals("newUsername", user.getUsername());
    }

    @Test
    @DisplayName("유저 도메인 엔티티 운동 매칭 게시물 추가 테스트")
    void testAddMatePosts() {
        List<Long> matePostIds = new ArrayList<>();
        matePostIds.add(1L);
        user = User.builder()
                .matePostIds(matePostIds)
                .build();

        user.addMatePosts(2L);
        assertEquals(2, user.getMatePostIds().size());
        assertTrue(user.getMatePostIds().contains(1L));
        assertTrue(user.getMatePostIds().contains(2L));
    }
}