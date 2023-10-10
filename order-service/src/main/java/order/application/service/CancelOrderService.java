package order.application.service;

import global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.application.kafka.KafkaProducer;
import order.application.port.in.CancelOrderUseCase;
import order.application.port.out.LoadOrderItemPort;
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
    private final LoadOrderItemPort loadOrderItemPort;
    private final SaveOrderPort saveOrderPort;

    private final KafkaProducer kafkaProducer;

    @Override
    public void CancelOrder(Long orderId) {
        Order order = loadOrderPort.loadOrder(orderId);
        order.cancelOrder();
        saveOrderPort.saveOrder(order);

//        List<OrderItem> orderItems = loadOrderItemPort.loadOrderItemByOrderId(orderId);
//        for (OrderItem orderItem : orderItems) {
//            kafkaProducer.increaseStock("increase-stock", orderItem);
//        }
    }
}
