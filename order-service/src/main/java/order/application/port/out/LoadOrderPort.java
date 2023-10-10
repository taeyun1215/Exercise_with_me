package order.application.port.out;

import order.domain.Order;

import java.util.List;

public interface LoadOrderPort {

    Order loadOrder(Long orderId);

    List<Order> loadOrderByUser(Long userId);
}
