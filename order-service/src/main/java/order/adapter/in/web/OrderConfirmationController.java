package order.adapter.in.web;

import global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import order.adapter.out.persistence.order.OrderResponseMapper;
import order.application.port.in.ConfirmationOrderUseCase;
import order.domain.OrderConfirmation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderConfirmationController {

    private final ConfirmationOrderUseCase confirmationOrderUseCase;
    private final OrderResponseMapper orderResponseMapper;

    @GetMapping("/confirmation")
    public ResponseEntity<ReturnObject> confirmationOrder(
            HttpServletRequest httpServletRequest
    ) {
        Long userId = Long.valueOf(httpServletRequest.getHeader("LOGIN_MEMBER"));
        List<OrderConfirmation> orderConfirmations = confirmationOrderUseCase.confirmationOrder(userId);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(orderResponseMapper.mapToConfirmationOrderResponse(orderConfirmations))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

}
