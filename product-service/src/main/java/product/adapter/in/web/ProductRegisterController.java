package product.adapter.in.web;

import global.annotation.LoginUser;
import global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import product.adapter.in.request.RegisterProductRequest;
import product.application.port.in.RegisterProductUseCase;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductRegisterController {

    private final RegisterProductUseCase registerProductUseCase;

    @PostMapping("/register")
    public ResponseEntity<ReturnObject> registerProduct(
            HttpServletRequest httpServletRequest,
            @RequestBody RegisterProductRequest registerUserRequest
    ) {
        Long userId = Long.valueOf(httpServletRequest.getHeader("LOGIN_MEMBER"));

        registerProductUseCase.registerProduct(registerUserRequest, userId);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data("상품 추가가 완료 되었습니다.")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

}
