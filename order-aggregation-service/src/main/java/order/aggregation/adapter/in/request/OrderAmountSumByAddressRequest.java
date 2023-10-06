package order.aggregation.adapter.in.request;

import global.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class OrderAmountSumByAddressRequest extends SelfValidating<OrderAmountSumByAddressRequest> {

    private String address;

    public OrderAmountSumByAddressRequest(String address) {
        this.address = address;
        this.validateSelf();
    }

}
