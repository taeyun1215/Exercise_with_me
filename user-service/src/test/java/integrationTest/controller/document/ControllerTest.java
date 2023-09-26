package integrationTest.controller.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import user.UserServiceApplication;
import user.adapter.out.persistence.UserResponseMapper;
import user.application.port.in.query.CheckNicknameQuery;
import user.application.port.in.query.CheckUsernameQuery;
import user.application.port.in.usecase.RegisterUserUseCase;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ActiveProfiles("test")
@SpringBootTest(classes = UserServiceApplication.class)
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

}
