package order.adapter.in.web;

import global.annotation.LoginUser;
import global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import order.application.port.in.CancelOrderUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderCancelController {

    private final CancelOrderUseCase cancelOrderUseCase;

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ReturnObject> cancelOrder(
            HttpServletRequest httpServletRequest,
            @PathVariable("orderId") Long orderId
    ) {
        Long userId = Long.valueOf(httpServletRequest.getHeader("LOGIN_MEMBER"));
        cancelOrderUseCase.CancelOrder(orderId, userId);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data("주문을 취소하였습니다.")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);

    }
}
