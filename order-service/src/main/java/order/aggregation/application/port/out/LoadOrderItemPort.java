package order.aggregation.application.port.out;

import order.domain.OrderItem;

import java.util.List;

public interface LoadOrderItemPort {

    List<OrderItem> loadOrderItemByOrderId(Long orderId);

}
