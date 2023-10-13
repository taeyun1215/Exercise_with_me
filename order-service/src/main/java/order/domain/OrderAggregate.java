package order.domain;

import global.event.StockReducedEvent;
import global.event.StockSoldOutEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.application.port.in.command.CreateOrderCommand;
import order.domain.constant.OrderStatus;
import global.event.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate
@NoArgsConstructor
public class OrderAggregate {

    @AggregateIdentifier
    private Long orderId;

    private OrderStatus orderStatus; // 주문 상태 (ex: CREATED, COMPLETED, CANCELLED)
    private List<OrderCreatedEvent.OrderItemInfo> orderItems;

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        this.orderId = command.getOrderId();
        apply(new OrderCreatedEvent(command.getOrderId(), command.getUserId(), command.getOrderItemInfos()));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderId = event.getOrderId();
        this.orderItems = event.getOrderItems();
        this.orderStatus = OrderStatus.ORDER_CREATED;
    }

    @EventSourcingHandler
    public void on(StockReducedEvent event) {
        this.orderId = event.getOrderId();
    }

    @EventSourcingHandler
    public void on(StockSoldOutEvent event) {
        this.orderId = event.getOrderId();
        this.orderStatus = OrderStatus.ORDER_CANCEL;
    }

//    @CommandHandler
//    public void handle(CompleteOrderCommand command) {
//        Assert.isTrue(orderStatus == OrderStatus.ORDER_CREATED,
//                () -> "Order can only be completed if it's in CREATED state");
//        apply(new OrderCompletedEvent(command.getOrderId()));
//    }
//
//    @EventSourcingHandler
//    public void on(OrderCompletedEvent event) {
//        this.orderStatus = OrderStatus.ORDER_COMPLETE;
//    }

//    @CommandHandler
//    public void handle(CancelOrderCommand command) {
//        log.info("3333333");
//        Assert.isTrue(orderStatus == OrderStatus.ORDER_CREATED,
//                () -> "Order can only be cancelled if it's in CREATED state");
//        apply(new OrderCancelledEvent(command.getOrderId()));
//    }
//
//    @EventSourcingHandler
//    public void on(OrderCancelledEvent event) {
//        log.info("444444");
//        this.orderStatus = OrderStatus.ORDER_CANCEL;
//    }
}
