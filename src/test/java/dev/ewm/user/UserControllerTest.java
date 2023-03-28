package dev.ewm.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ewm.domain.user.UserService;
import dev.ewm.domain.user.request.UserRegisterRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/* @RunWith : JUnit 프레임워크가 테스트를 실행할 시 테스트 실행방법을 확장할 때 쓰는 어노테이션
 * @WebMvcTest : MVC를 위한 테스트, 컨트롤러가 예상대로 동작하는지 테스트하는데 사용됩니다. Web과 관련된 다음 어노테이션만 스캔합니다.
        (@Controller, @ControllerAdvice, @JsonComponent, Converter, GenericConverter, Filter, HandlerInterceptor, WebMvcConfigurer, HandlerMethodArgumentResolver)
 * @AutoConfigureMockMvc : @WebMvcTest와 비슷하지만 가장 큰 차이점은 컨트롤러 뿐만 아니라 테스트 대상이 아닌 @Service나 @Repository가 붙은 객체들도 모두 메모리에 올립니다.
 * @Transactional : 선언적 트랜잭션을 지원하는 어노테이션입니다. 테스트환경에서의 @Transactional은 메소드가 종료될 때 자동으로 롤백됩니다.
 * */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Test // @Test : 테스트가 수행되는 메소드를 가르킨다.
    @DisplayName("회원가입 성공 테스트 상태값 201을 반환한다.")
    public void joinUserSuccessTest() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();

        // Given
        userRegisterRequest.setUsername("test11111");
        userRegisterRequest.setPassword("taeyun1215");
        userRegisterRequest.setConfirmPassword("taeyun1215");
        userRegisterRequest.setNickname("test22222");
        userRegisterRequest.setPhone("11122223333");
        userRegisterRequest.setEmail("test11111@naver.com");

        String content = objectMapper.writeValueAsString(userRegisterRequest);

        // When
        mockMvc.perform(post("/users/register")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
//                .andExpect(jsonPath("$.data.user.username").value("이태윤"));
//                .andExpect(jsonPath("$.data.user.password").value("taeyun1215"))
//                .andExpect(jsonPath("$.data.user.confirmPassword").value("taeyun1215"))
//                .andExpect(jsonPath("$.data.user.nickname").value("devty"))
//                .andExpect(jsonPath("$.data.user.phone").value("01024156806"))
//                .andExpect(jsonPath("$.data.user.email").value("taeyun1215@naver.com"));

    }
}
