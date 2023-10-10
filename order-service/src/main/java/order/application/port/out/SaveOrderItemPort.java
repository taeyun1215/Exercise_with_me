package order.application.port.out;

import order.domain.OrderItem;

public interface SaveOrderItemPort {

    void saveOrderItem(OrderItem orderItem);

}
