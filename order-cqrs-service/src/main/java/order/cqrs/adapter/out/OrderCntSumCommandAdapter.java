package order.cqrs.adapter.out;

import global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import order.cqrs.application.port.in.OrderCntSumByAddressQuery;
import order.cqrs.application.port.out.InsertOrderCntStatePort;
import order.cqrs.domain.OrderCntSumByAddress;
import org.axonframework.queryhandling.QueryHandler;

@PersistenceAdapter
@RequiredArgsConstructor
public class OrderCntSumCommandAdapter implements InsertOrderCntStatePort {

    private final OrderCntSumByAddressRepo repo;

    @Override
    public void InsertOrderCntByAddress(String addressName, int increaserderCnt) {
        // task 1 & task 2 : 주소에 대해 RDB컬럼이 있는 지 확인하고 없다면 새로운 레코드를 만듭니다.
        OrderCntSumByAddress orderCntSumByAddress = repo.findByAddress(addressName)
                .orElseGet(() -> createNewRecord(addressName));

        // task 3 : 기존 레코드를 가져와서 연산을 해주고 다시 저장(업데이트)합니다.
        orderCntSumByAddress.setOrderCnt(orderCntSumByAddress.getOrderCnt() + increaserderCnt);
        repo.save(orderCntSumByAddress);
    }

    private OrderCntSumByAddress createNewRecord(String addressName) {
        OrderCntSumByAddress newRecord = OrderCntSumByAddress.builder()
                .address(addressName)
                .orderCnt(0L)
                .build();

        return repo.save(newRecord);
    }
}
