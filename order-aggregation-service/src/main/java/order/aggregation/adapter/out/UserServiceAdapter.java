package order.aggregation.adapter.out;

import global.annotation.ExternalSystemAdapter;
import global.common.CommonHttpClient;
import lombok.RequiredArgsConstructor;
import order.aggregation.application.port.out.GetUserPort;

import java.util.List;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class UserServiceAdapter implements GetUserPort {

    private final CommonHttpClient commonHttpClient;
    private final String userServiceUrl = "localhost:8000/user-service";

    @Override
    public List<Long> getUserIdByAddress(String address) {
        return null;
    }

}
