package order.application.handlers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.application.port.in.CancelOrderUseCase;
import order.application.port.in.RegisterOrderUseCase;
import order.application.port.in.command.CancelOrderCommand;
import order.application.port.in.command.CompleteOrderCommand;
import order.domain.events.OrderCancelledEvent;
import order.domain.events.OrderCompletedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCommandHandler {

    @Autowired
    private EventGateway eventGateway;

    private final RegisterOrderUseCase registerOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;

    @CommandHandler
    public void handle(CompleteOrderCommand command) {
//        registerOrderUseCase.completeOrder(command.getOrderId());
//        eventGateway.publish(new OrderCompletedEvent(command.getOrderId()));
        log.info("Order completed with ID: " + command.getOrderId());
    }

    @CommandHandler
    public void handle(CancelOrderCommand command) {
        cancelOrderUseCase.CancelOrder(1L);
        log.info("555555");
        eventGateway.publish(new OrderCancelledEvent(command.getOrderId()));
        log.info("Order cancel with ID: " + command.getOrderId());
    }

}
