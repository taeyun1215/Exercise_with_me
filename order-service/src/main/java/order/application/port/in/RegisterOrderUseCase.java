package order.application.port.in;

import order.adapter.in.request.OrderRegisterRequest;
import order.application.port.in.command.CreateOrderCommand;

public interface RegisterOrderUseCase {

    void registerOrder(CreateOrderCommand command);

    void completeOrder(Long orderId);

}
