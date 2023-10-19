package order.cqrs.adapter.out;

import global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import order.cqrs.application.port.in.OrderCntSumByAddressQuery;
import order.cqrs.domain.OrderCntSumByAddress;
import org.axonframework.queryhandling.QueryHandler;

@PersistenceAdapter
@RequiredArgsConstructor
public class OrderCntSumQueryAdapter {

    private final OrderCntSumByAddressRepo repo;

    @QueryHandler
    public Long orderCntSumByAddress(OrderCntSumByAddressQuery query) {
        return repo.findByAddress(query.getAddress())
                .map(OrderCntSumByAddress::getOrderCnt)
                .orElse(null);
    }
}
