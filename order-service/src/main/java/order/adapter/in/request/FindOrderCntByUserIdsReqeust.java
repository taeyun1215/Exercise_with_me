package order.adapter.in.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import global.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
public class FindOrderCntByUserIdsReqeust extends SelfValidating<FindOrderCntByUserIdsReqeust> {

    private List<Long> userIds;

    public FindOrderCntByUserIdsReqeust(
            @JsonProperty("userIds") List<Long> userIds
    ) {
        this.userIds = userIds;
        this.validateSelf();
    }
}
