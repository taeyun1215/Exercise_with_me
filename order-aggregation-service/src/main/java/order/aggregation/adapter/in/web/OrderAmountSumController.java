package order.aggregation.adapter.in.web;

import global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import order.aggregation.adapter.in.request.OrderAmountSumByAddressRequest;
import order.aggregation.application.port.in.OrderAmountSumByAddressUseCase;
import order.aggregation.application.port.in.command.OrderAmountSumByAddressCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderAmountSumController {

    private final OrderAmountSumByAddressUseCase orderAmountSumByAddressUseCase;

    @PostMapping("/aggregation/get-order-amount-sum-by-address")
    public ResponseEntity<ReturnObject> OrderAmountSumByAddress(
            OrderAmountSumByAddressRequest request
    ) {
        OrderAmountSumByAddressCommand command = OrderAmountSumByAddressCommand.builder()
                .address(request.getAddress())
                .build();

        int orderAmountSum = orderAmountSumByAddressUseCase.OrderAmountSumByAddress(command);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(orderAmountSum)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);

    }
}
