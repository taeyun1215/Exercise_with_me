package order.application.port.in.command;

import lombok.Builder;
import lombok.Data;
import order.domain.events.OrderCreatedEvent;

import java.util.List;

@Builder
@Data
public class CreateOrderCommand {

    private String orderId;
    private Long userId;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private List<OrderCreatedEvent.OrderItemInfo> orderItemInfos;

}
