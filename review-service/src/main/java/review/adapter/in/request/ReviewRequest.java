package review.adapter.in.request;

import global.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class ReviewRequest extends SelfValidating<ReviewRequest> {

}
