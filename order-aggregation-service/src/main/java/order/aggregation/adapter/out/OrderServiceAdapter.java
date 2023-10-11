package order.aggregation.adapter.out;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.annotation.ExternalSystemAdapter;
import global.common.CommonHttpClient;
import lombok.RequiredArgsConstructor;
import order.aggregation.application.port.out.GetOrderPort;

import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class OrderServiceAdapter implements GetOrderPort {

    ObjectMapper mapper = new ObjectMapper();
    private final CommonHttpClient commonHttpClient;
    private final String orderServiceUrl = "http://localhost:8000/order-service";

    @Override
    public List<Integer> getOrderCntByUserIds(List<Long> userIds) {
        String url = String.join("/", orderServiceUrl, "orders/order_count");
        try {
            Map<String, List<Long>> payload = Collections.singletonMap("userIds", userIds);
            String jsonPayload = mapper.writeValueAsString(payload);

            long startTime = System.currentTimeMillis();

            CompletableFuture<HttpResponse<String>> futureResponse = commonHttpClient.sendAsyncPostRequest(url, jsonPayload);

            long endTime = System.currentTimeMillis();
            System.out.println("Order-service API Call time: " + (endTime - startTime) + " ms");

            // JSON 응답을 맵으로 변환합니다.
            Map<String, Object> responseMap = mapper.readValue(futureResponse.get().body(), new TypeReference<Map<String, Object>>() {});
            List<Integer> orderCntList = (List<Integer>) responseMap.get("data");

            return orderCntList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
