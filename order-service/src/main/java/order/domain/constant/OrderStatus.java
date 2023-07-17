package order.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    ORDER_CREATED("주문시작"),
    ORDER_CANCEL("주문취소"),
    PAYED("결제완료"),
    BEING_PREPARED("배송준비중"),
    SHIPPING("배송중"),
    DELIVERED("배송완료");

    private final String value;

}
