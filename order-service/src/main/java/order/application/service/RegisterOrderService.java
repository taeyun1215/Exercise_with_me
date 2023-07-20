package order.application.service;

import global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.adapter.in.request.OrderItemRegisterRequest;
import order.adapter.in.request.OrderRegisterRequest;
import order.application.kafka.KafkaProducer;
import order.application.port.in.RegisterOrderUseCase;
import order.application.port.out.SaveOrderItemPort;
import order.application.port.out.SaveOrderPort;
import order.domain.Order;
import order.domain.OrderItem;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterOrderService implements RegisterOrderUseCase {

    private final SaveOrderPort saveOrderPort;
    private final SaveOrderItemPort saveOrderItemPort;

    private final KafkaProducer kafkaProducer;

    @Override
    public void registerOrder(Long userId, OrderRegisterRequest orderRegisterRequest) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRegisterRequest orderItemRegisterRequest : orderRegisterRequest.getOrderItemRegisterRequests()) {
            OrderItem orderItem = orderItemRegisterRequest.toEntity();
            orderItems.add(orderItem);
        }
        Order order = orderRegisterRequest.toEntity(userId);

        for (OrderItem orderItem : orderItems) {
            kafkaProducer.reduceStock("reduce-stock", orderItem);
        }
        save(order, orderItems);
    }

    private void save(Order order, List<OrderItem> orderItems) {
        saveOrderPort.saveOrder(order);
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(order.getOrderId()); // todo : order 저장하고 나서 싱글톤이라 같은 객체를 사용하는줄 알았는데 아님.
            saveOrderItemPort.saveOrderItem(orderItem);
        }
    }

}
