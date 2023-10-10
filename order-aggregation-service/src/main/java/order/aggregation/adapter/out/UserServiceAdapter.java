package order.aggregation.adapter.out;

import com.fasterxml.jackson.databind.ObjectMapper;
import global.annotation.ExternalSystemAdapter;
import global.common.CommonHttpClient;
import lombok.RequiredArgsConstructor;
import order.aggregation.application.port.out.GetUserPort;

import java.util.List;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class UserServiceAdapter implements GetUserPort {

    private final CommonHttpClient commonHttpClient;
    private final String userServiceUrl = "user-service";

    @Override
    public List<Long> getUserIdByAddress(String address) {
        String url = String.join("/", userServiceUrl, "users/address", address);
        try {
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();

            ObjectMapper mapper = new ObjectMapper();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
