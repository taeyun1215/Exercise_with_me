package order.cqrs.application.port.in;

public interface OrderCntSumUseCase {

    Long orderCntSumByAddress(OrderCntSumByAddressQuery orderCntSumByAddressQuery);

}
