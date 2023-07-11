package mate.adapter.in.web.matePost;

import lombok.RequiredArgsConstructor;
import mate.adapter.in.request.CreateMatePostRequest;
import mate.application.port.in.usecase.CreateMatePostUseCase;
import mate.global.kafka.KafkaProducer;
import mate.global.utils.ReturnObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matePost")
public class MatePostCreateController {

    private final CreateMatePostUseCase createMatePostUseCase;
    private final KafkaProducer kafkaProducer;

    @PostMapping("/create")
    public ResponseEntity<ReturnObject> createMatePost(
            HttpServletRequest httpServletRequest,
            @RequestBody CreateMatePostRequest createMatePostRequest
    ) {
        Long userId = Long.valueOf(httpServletRequest.getHeader("LOGIN_MEMBER"));
        createMatePostUseCase.createMatePost(createMatePostRequest, userId);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data("생성이 완료 되었습니다.")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }
}
