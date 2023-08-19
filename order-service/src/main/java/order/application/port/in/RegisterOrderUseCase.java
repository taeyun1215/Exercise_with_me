package order.application.port.in;

import order.adapter.in.request.OrderRegisterRequest;

public interface RegisterOrderUseCase {

    void registerOrder(Long userId, OrderRegisterRequest orderRegisterRequest);

    void completeOrder(Long orderId);

}
