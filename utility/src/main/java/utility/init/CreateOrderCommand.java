package utility.init;

import lombok.Builder;
import lombok.Data;

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
