package order.aggregation.application.port.out;

import java.util.List;

public interface GetUserPort {

    List<Long> getUserIdByAddress(String address);

}
