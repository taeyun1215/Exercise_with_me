package order.adapter.in.request;

import global.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;
import order.domain.Order;
import order.domain.constant.OrderStatus;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
public class OrderRegisterRequest extends SelfValidating<OrderRegisterRequest> {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String receiverName;

    @NotBlank(message = "핸드폰 번호는 필수 입력 값입니다.")
    private String receiverPhone;

    @NotBlank(message = "주소는 필수 입력 값입니다.")
    private String receiverAddress;

    @Valid
    @NotEmpty(message = "상품은 필수 입력 값입니다.")
    private List<OrderItemRegisterRequest> orderItemRegisterRequests;

    public OrderRegisterRequest(
            String receiverName,
            String receiverPhone,
            String receiverAddress,
            List<OrderItemRegisterRequest> orderItemRegisterRequests
    ) {
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverAddress = receiverAddress;
        this.orderItemRegisterRequests = orderItemRegisterRequests;
        this.validateSelf();
    }

    public Order toEntity(Long userId) {
        return Order.builder()
                .receiverName(receiverName)
                .receiverPhone(receiverPhone)
                .receiverAddress(receiverAddress)
                .userId(userId)
                .orderStatus(OrderStatus.ORDER_CREATED)
                .build();
    }

}
