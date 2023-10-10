package order.aggregation.adapter.out;

import com.fasterxml.jackson.databind.ObjectMapper;
import global.annotation.ExternalSystemAdapter;
import global.common.CommonHttpClient;
import lombok.RequiredArgsConstructor;
import order.aggregation.application.port.out.GetOrderPort;

import java.util.List;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class OrderServiceAdapter implements GetOrderPort {

    ObjectMapper mapper = new ObjectMapper();
    private final CommonHttpClient commonHttpClient;
    private final String orderServiceUrl = "http://localhost:8000/order-service";

    @Override
    public List<Integer> getOrderCntByUserIds(List<Long> userIds) {
        String url = String.join("/", orderServiceUrl, "/orders/order_cnt");
        try {
            String jsonResponse = commonHttpClient.sendPostRequest(url, mapper.writeValueAsString(userIds)).body();

            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
