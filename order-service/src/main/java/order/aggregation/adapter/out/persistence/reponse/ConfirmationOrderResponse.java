package order.aggregation.adapter.out.persistence.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import order.domain.constant.OrderStatus;

import java.util.List;

@Getter
@Builder
public class ConfirmationOrderResponse {

    private Long orderId;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private OrderStatus orderStatus;
    private int orderTotalPrice;

    private List<ConfirmationOrderItemResponse> confirmationOrderItemResponses;

    @Getter
    @AllArgsConstructor
    public static class ConfirmationOrderItemResponse {
        private String name;
        private int productTotalPrice; // price * count
    }

}
