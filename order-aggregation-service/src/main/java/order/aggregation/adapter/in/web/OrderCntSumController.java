package order.aggregation.adapter.in.web;

import global.annotation.WebAdapter;
import global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import order.aggregation.adapter.in.request.OrderCntSumByAddressRequest;
import order.aggregation.application.port.in.OrderCntSumByAddressUseCase;
import order.aggregation.application.port.in.command.OrderCntSumByAddressCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderCntSumController {

    private final OrderCntSumByAddressUseCase orderCntSumByAddressUseCase;

    @PostMapping("/aggregation/get-order-cnt-sum-by-address")
    public ResponseEntity<ReturnObject> OrderCntSumByAddress(
            @RequestBody OrderCntSumByAddressRequest request
    ) {
        OrderCntSumByAddressCommand command = OrderCntSumByAddressCommand.builder()
                .address(request.getAddress())
                .build();

        int orderAmountSum = orderCntSumByAddressUseCase.OrderCntSumByAddress(command);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(orderAmountSum)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }
}
