package unitTest.classicist.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.application.port.out.LoadUserPort;
import user.application.service.CheckNicknameService;
import user.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("CheckNicknameService 단위 테스트")
public class CheckNicknameServiceTest {

    private CheckNicknameService checkNicknameService;

    @BeforeEach
    void setUp() {
        LoadUserPort loadUserPort = new LoadUserPortFake();
        checkNicknameService = new CheckNicknameService(loadUserPort);
    }

    @Test
    @DisplayName("중복 닉네임 확인 요청에 대한 성공 응답 반환")
    void testCheckNicknameExistingUser() {
        User user = checkNicknameService.checkNickname("existingNickname");
        assertEquals("existingNickname", user.getNickname());
    }

    @Test
    @DisplayName("중복 닉네임 확인 요청에 대한 실패 응답 반환")
    void testCheckNicknameNonExistingUser() {
        User user = checkNicknameService.checkNickname("nonExistingNickname");
        assertNull(user);
    }

    // LoadUserPort 인터페이스를 구현한 Fake 클래스
    // 가짜 구현체 (Fake Implementation)를 사용하는 주요 이유는 단위 테스트를 간소화하고 외부 시스템 또는 구성 요소에 의존하지 않도록 하기 위함.
    private static class LoadUserPortFake implements LoadUserPort {

        @Override
        public User findByNickname(String nickname) {
            if ("existingNickname".equals(nickname)) {
                return User.builder()
                        .userId(1L)
                        .username("testuser")
                        .password("testpassword")
                        .nickname("existingNickname")
                        .phone("123456789")
                        .email("test@test.com")
                        .build();
            }
            return null;
        }

        // 해당 테스트에서는 사용하지 않지만 오버라이딩 해줘야하므로 구현해둠.
        @Override
        public User findByUsername(String username) {
            return null;
        }
    }
}
