package order.application.port.out;

import order.domain.Order;

public interface SaveOrderPort {

    Order saveOrder(Order order);

}
