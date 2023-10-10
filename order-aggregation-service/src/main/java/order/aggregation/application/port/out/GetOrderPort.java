package order.aggregation.application.port.out;

import java.util.List;

public interface GetOrderPort {

    List<Integer> getMoneyByUserIds(List<Long> userIds);

}
