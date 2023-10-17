package order.cqrs.adapter.in.web;

import global.annotation.WebAdapter;
import global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import order.cqrs.application.port.in.OrderCntSumByAddressQuery;
import order.cqrs.application.port.in.OrderCntSumUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderCqrsController {

    private final OrderCntSumUseCase orderCntSumUseCase;

    @GetMapping("/cqrs/get-order-cnt-sum-by-address/{address}")
    public ResponseEntity<ReturnObject> OrderCntSumByAddress(
            @PathVariable("address") String address
    ) {
        OrderCntSumByAddressQuery query = OrderCntSumByAddressQuery.builder()
                .address(address)
                .build();

        long startTime = System.currentTimeMillis();

        Long orderCntSumByAddress = orderCntSumUseCase.orderCntSumByAddress(query);

        long endTime = System.currentTimeMillis();
        System.out.println("Order-aggregation-service API Call time: " + (endTime - startTime) + " ms");

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(orderCntSumByAddress)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }
}
