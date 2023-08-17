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
import order.domain.events.OrderCreatedEvent;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    private EventGateway eventGateway;

    @Override
    public void registerOrder(Long userId, OrderRegisterRequest orderRegisterRequest) {
        List<OrderItem> orderItems = new ArrayList<>();

        // todo : 주문 생성후 orchestration에 보내주기
        // eventGateway.publish(new OrderCreatedEvent(order.getId(), order.getProductId(), order.getCount()));

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
