package order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import order.application.port.in.command.CancelOrderCommand;
import order.application.port.in.command.CompleteOrderCommand;
import order.application.port.in.command.CreateOrderCommand;
import order.domain.constant.OrderStatus;
import order.domain.events.OrderCancelledEvent;
import order.domain.events.OrderCompletedEvent;
import order.domain.events.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.common.Assert;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class OrderAggregate {

    @AggregateIdentifier
    private Long orderId;

    private OrderStatus orderStatus; // 주문 상태 (ex: CREATED, COMPLETED, CANCELLED)
    private List<OrderCreatedEvent.OrderItemInfo> orderItems;

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        apply(new OrderCreatedEvent(command.getOrderId(), command.getOrderItemInfos()));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderId = event.getOrderId();
        this.orderItems = event.getOrderItems();
        this.orderStatus = OrderStatus.ORDER_CREATED;
    }

    @CommandHandler
    public void handle(CompleteOrderCommand command) {
        Assert.isTrue(orderStatus == OrderStatus.ORDER_CREATED,
                () -> "Order can only be completed if it's in CREATED state");
        apply(new OrderCompletedEvent(command.getOrderId()));
    }

    @EventSourcingHandler
    public void on(OrderCompletedEvent event) {
        this.orderStatus = OrderStatus.ORDER_COMPLETE;
    }

    @CommandHandler
    public void handle(CancelOrderCommand command) {
        Assert.isTrue(orderStatus == OrderStatus.ORDER_CREATED,
                () -> "Order can only be cancelled if it's in CREATED state");
        apply(new OrderCancelledEvent(command.getOrderId()));
    }

    @EventSourcingHandler
    public void on(OrderCancelledEvent event) {
        this.orderStatus = OrderStatus.ORDER_CANCEL;
    }
}
