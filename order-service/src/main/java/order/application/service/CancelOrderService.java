package order.application.service;

import global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.application.port.in.CancelOrderUseCase;
import order.application.port.out.LoadOrderPort;
import order.application.port.out.SaveOrderPort;
import order.domain.Order;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class CancelOrderService implements CancelOrderUseCase {

    private final LoadOrderPort loadOrderPort;
    private final SaveOrderPort saveOrderPort;

    @Override
    public void CancelOrder(Long orderId, Long userId) {
        Order order = loadOrderPort.loadOrder(orderId);
        order.cancelOrder();
        saveOrderPort.saveOrder(order);
    }
}
