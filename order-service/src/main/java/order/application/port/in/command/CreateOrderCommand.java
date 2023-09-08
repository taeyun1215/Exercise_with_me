package order.application.port.in.command;

import lombok.Builder;
import lombok.Data;
import order.domain.events.OrderCreatedEvent;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Builder
@Data
public class CreateOrderCommand {

//    @TargetAggregateIdentifier
    private Long orderId;
    private Long userId;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private List<OrderCreatedEvent.OrderItemInfo> orderItemInfos;

}
