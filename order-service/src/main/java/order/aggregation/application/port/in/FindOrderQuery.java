package order.aggregation.application.port.in;

import java.util.List;

public interface FindOrderQuery {

    List<Integer> findOrderCntListByUserIds(List<Long> userIds);

}
