package order.aggregation.adapter.out.persistence.order;

import order.aggregation.adapter.out.persistence.reponse.ConfirmationOrderResponse;
import order.domain.Order;
import order.domain.OrderConfirmation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderResponseMapper {

    // 주문 목록 리스폰
    public List<ConfirmationOrderResponse> mapToConfirmationOrderResponse(List<OrderConfirmation> orderConfirmations) {
        List<ConfirmationOrderResponse> confirmationOrderResponses = new ArrayList<>();

        for (OrderConfirmation orderConfirmation : orderConfirmations) {
            Order order = orderConfirmation.getOrder();
            List<OrderConfirmation.OrderItemProduct> orderItemProducts = orderConfirmation.getOrderItemProducts();

            ConfirmationOrderResponse confirmationOrderResponse = ConfirmationOrderResponse.builder()
                    .orderId(order.getOrderId())
                    .receiverName(order.getReceiverName())
                    .receiverPhone(order.getReceiverPhone())
                    .receiverAddress(order.getReceiverAddress())
                    .orderStatus(order.getOrderStatus())
                    .orderTotalPrice(
                            orderItemProducts
                                    .stream()
                                    .mapToInt(OrderConfirmation.OrderItemProduct::getProductTotalPrice)
                                    .sum()
                    )
                    .confirmationOrderItemResponses(
                            orderItemProducts
                                    .stream()
                                    .map(orderItemProduct -> {
                                        return new ConfirmationOrderResponse.ConfirmationOrderItemResponse(
                                                orderItemProduct.getName(),
                                                orderItemProduct.getProductTotalPrice()
                                        );
                                    })
                                    .collect(Collectors.toList())
                    )
                    .build();

            confirmationOrderResponses.add(confirmationOrderResponse);
        }

       return confirmationOrderResponses;
    }

}
