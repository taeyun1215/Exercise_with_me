package order.aggregation.adapter.in.request;

import global.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
public class FindOrderCntByUserIdsReqeust extends SelfValidating<FindOrderCntByUserIdsReqeust> {

    private List<Long> userIds;

    public FindOrderCntByUserIdsReqeust(
            List<Long> userIds
    ) {
        this.userIds = userIds;
        this.validateSelf();
    }
}
