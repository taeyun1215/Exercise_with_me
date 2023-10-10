package order.adapter.in.web;

import global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import order.adapter.in.request.FindOrderCntByUserIdsReqeust;
import order.application.port.in.FindOrderQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderFindController {

    private final FindOrderQuery query;

    @PostMapping("/order_count")
    public ResponseEntity<ReturnObject> findOrderMoneyByUserIds(
            @RequestBody FindOrderCntByUserIdsReqeust reqeust
    ) {
        List<Integer> orderCntList = query.findOrderCntListByUserIds(reqeust.getUserIds());

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(orderCntList)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }
}
