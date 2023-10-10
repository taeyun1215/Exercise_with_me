package order.aggregation.application.port.out;

import java.util.List;

public interface GetOrderPort {

    List<Integer> getOrderCntByUserIds(List<Long> userIds);

}
