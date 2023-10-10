package order.aggregation.adapter.in.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import global.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class OrderCntSumByAddressRequest extends SelfValidating<OrderCntSumByAddressRequest> {

    private String address;

    public OrderCntSumByAddressRequest(@JsonProperty("address") String address) {
        this.address = address;
        this.validateSelf();
    }

}
