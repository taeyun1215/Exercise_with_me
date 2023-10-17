package order.cqrs.application.service;

import global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.cqrs.application.port.in.OrderCntSumByAddressQuery;
import order.cqrs.application.port.in.OrderCntSumUseCase;
import org.axonframework.queryhandling.QueryGateway;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class OrderCntSumService implements OrderCntSumUseCase {

    private final QueryGateway queryGateway;

    @Override
    public Long orderCntSumByAddress(OrderCntSumByAddressQuery query) {
        return queryGateway.query(query, Long.class).join();
    }
}
