package order.application.port.out;

import order.domain.Order;

public interface SaveOrderPort {

    void saveOrder(Order order);

}
