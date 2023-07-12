package user.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import global.utils.ReturnObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserLogoutController {

    @PostMapping("/logout")
    public ResponseEntity<ReturnObject> logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

}
