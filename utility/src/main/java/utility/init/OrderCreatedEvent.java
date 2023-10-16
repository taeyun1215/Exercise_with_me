package utility.init;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

public class OrderCreatedEvent {

    @TargetAggregateIdentifier
    private final Long orderId;
    private final Long userId;
    private final List<OrderItemInfo> orderItems;

    public OrderCreatedEvent(Long orderId, Long userId, List<OrderItemInfo> orderItems) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderItems = orderItems;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public List<OrderItemInfo> getOrderItems() {
        return orderItems;
    }

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", orderItems=" + orderItems +
                '}';
    }

    public static class OrderItemInfo {
        private final Long productId;
        private final int count;

        public OrderItemInfo(Long productId, int count) {
            this.productId = productId;
            this.count = count;
        }

        public Long getProductId() {
            return productId;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return "OrderItemInfo{" +
                    "productId=" + productId +
                    ", count=" + count +
                    '}';
        }
    }
}
