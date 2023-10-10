package order.aggregation.adapter.in.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import global.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class OrderAmountSumByAddressRequest extends SelfValidating<OrderAmountSumByAddressRequest> {

    private String address;

    public OrderAmountSumByAddressRequest(@JsonProperty("address") String address) {
        this.address = address;
        this.validateSelf();
    }

}
