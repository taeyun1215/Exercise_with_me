package order.application.port.in;

import order.domain.OrderConfirmation;

import java.util.List;

public interface ConfirmationOrderUseCase {

    List<OrderConfirmation> confirmationOrder(Long userId);

}
