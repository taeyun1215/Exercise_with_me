package integrationTest.controller.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import user.UserServiceApplication;

@Transactional
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ActiveProfiles("test")
@SpringBootTest(classes = UserServiceApplication.class)
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

}
