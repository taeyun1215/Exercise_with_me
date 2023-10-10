package order.aggregation.application.port.out;

import order.domain.OrderItem;

public interface SaveOrderItemPort {

    void saveOrderItem(OrderItem orderItem);

}
