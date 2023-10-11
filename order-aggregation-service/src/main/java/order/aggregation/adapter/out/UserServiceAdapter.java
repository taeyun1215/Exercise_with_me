package order.aggregation.adapter.out;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.annotation.ExternalSystemAdapter;
import global.common.CommonHttpClient;
import lombok.RequiredArgsConstructor;
import order.aggregation.application.port.out.GetUserPort;

import java.util.List;
import java.util.Map;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class UserServiceAdapter implements GetUserPort {

    ObjectMapper mapper = new ObjectMapper();
    private final CommonHttpClient commonHttpClient;
    private final String userServiceUrl = "http://localhost:8000/user-service";

    @Override
    public List<Long> getUserIdByAddress(String address) {
        String url = String.join("/", userServiceUrl, "users/address", address);
        try {
            long startTime = System.currentTimeMillis();

            String jsonResponse = commonHttpClient.sendGetRequest(url).body();

            long endTime = System.currentTimeMillis();
            System.out.println("User-service API Call time: " + (endTime - startTime) + " ms");

            // JSON 응답을 맵으로 변환합니다.
            Map<String, Object> responseMap = mapper.readValue(jsonResponse, new TypeReference<Map<String, Object>>() {});
            List<Long> userIds = (List<Long>) responseMap.get("data");

            return userIds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
