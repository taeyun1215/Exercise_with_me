package order.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class OrderConfirmation {

    private Order order;
    private List<OrderItemProduct> orderItemProducts;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class OrderItemProduct {
        private String name;
        private int productTotalPrice;
    }

}
