package order.application.handlers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.application.port.in.RegisterOrderUseCase;
import order.application.port.in.command.CompleteOrderCommand;
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

    @CommandHandler
    public void handle(CompleteOrderCommand command) {
        registerOrderUseCase.completeOrder(command.getOrderId());
        eventGateway.publish(new OrderCompletedEvent(command.getOrderId()));
        log.info("Order completed with ID: " + command.getOrderId());
    }
}
