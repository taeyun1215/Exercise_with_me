package order.adapter.in.web;

import global.annotation.LoginUser;
import global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import order.adapter.in.request.OrderRegisterRequest;
import order.application.port.in.RegisterOrderUseCase;
import order.application.port.in.command.CreateOrderCommand;
import order.domain.events.OrderCreatedEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderRegisterController {

    private final RegisterOrderUseCase registerOrderUseCase;

    @PostMapping("/register")
    public ResponseEntity<ReturnObject> registerOrder(
            HttpServletRequest httpServletRequest,
            @RequestBody OrderRegisterRequest orderRegisterRequest
    ) {
        Long userId = Long.valueOf(httpServletRequest.getHeader("LOGIN_MEMBER"));

        CreateOrderCommand command = CreateOrderCommand.builder()
                .userId(userId)
                .receiverName(orderRegisterRequest.getReceiverName())
                .receiverPhone(orderRegisterRequest.getReceiverPhone())
                .receiverAddress(orderRegisterRequest.getReceiverAddress())
                .orderItemInfos(
                        orderRegisterRequest.getOrderItemRegisterRequests().stream()
                                .map(request -> new OrderCreatedEvent.OrderItemInfo(request.getProductId(), request.getCount()))
                                .collect(Collectors.toList())
                )
                .build();

        registerOrderUseCase.registerOrder(command);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data("상품의 재고를 추가하였습니다.")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }

}
