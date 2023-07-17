package order.application.port.in;

public interface CancelOrderUseCase {

    void CancelOrder(Long orderId, Long userId);

}
