package order.cqrs.application.port.out;

import java.util.List;

public interface GetUserPort {

    String getAddressByUserId(Long userId);

}
