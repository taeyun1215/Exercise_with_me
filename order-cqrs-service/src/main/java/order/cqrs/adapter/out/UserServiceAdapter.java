package order.cqrs.adapter.out;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.annotation.ExternalSystemAdapter;
import global.common.CommonHttpClient;
import lombok.RequiredArgsConstructor;
import order.cqrs.application.port.out.GetUserPort;

import java.util.Map;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class UserServiceAdapter implements GetUserPort {

    ObjectMapper mapper = new ObjectMapper();
    private final CommonHttpClient commonHttpClient;
    private final String userServiceUrl = "http://localhost:8000/user-service";

    @Override
    public String getAddressByUserId(Long userId) {
        String url = String.join("/", userServiceUrl, "users/userId", String.valueOf(userId));
        try {
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();

            // JSON 응답을 맵으로 변환합니다.
            Map<String, Object> responseMap = mapper.readValue(jsonResponse, new TypeReference<Map<String, Object>>() {});
            String address = (String) responseMap.get("data");

            return address;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
