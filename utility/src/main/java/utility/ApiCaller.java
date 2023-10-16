package utility;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ApiCaller {

    public static void main(String[] args) {
        String apiUrl = "http://localhost:8000/order-service/orders/register";
        String loginMemberId = "12345"; // Replace with actual login member ID

        ObjectMapper objectMapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();

        IntStream.range(1, 2).forEach(i -> {
            // Construct the order register request payload
            OrderRegisterRequest orderRegisterRequest = createOrderRequest(i);

            // Convert the order register request to JSON
            String jsonPayload;
            try {
                jsonPayload = objectMapper.writeValueAsString(orderRegisterRequest);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to convert object to JSON", e);
            }

            // Set headers and body for HTTP request
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            headers.add("LOGIN_MEMBER", loginMemberId);
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);

            // Make API call
            ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

            // Optionally log success/failure
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                System.out.println("Request " + i + " succeeded: " + responseEntity.getBody());
            } else {
                System.out.println("Request " + i + " failed: " + responseEntity.getStatusCode());
            }
        });
    }

    private static OrderRegisterRequest createOrderRequest(int orderNumber) {
        return new OrderRegisterRequest(
                "Receiver " + orderNumber,
                "010-1234-567" + orderNumber,
                "Some Address " + orderNumber,
                createOrderItemRegisterRequests()
        );
    }

    private static List<OrderItemRegisterRequest> createOrderItemRegisterRequests() {
        // Replace with actual logic to generate order items
        return Collections.singletonList(new OrderItemRegisterRequest(1L, 2));
    }

    static class OrderRegisterRequest {
        String receiverName;
        String receiverPhone;
        String receiverAddress;
        List<OrderItemRegisterRequest> orderItemRegisterRequests;

        // Empty Constructor
        public OrderRegisterRequest() {}

        public OrderRegisterRequest(String receiverName, String receiverPhone, String receiverAddress, List<OrderItemRegisterRequest> orderItemRegisterRequests) {
            this.receiverName = receiverName;
            this.receiverPhone = receiverPhone;
            this.receiverAddress = receiverAddress;
            this.orderItemRegisterRequests = orderItemRegisterRequests;
        }

        // Getter methods
        public String getReceiverName() {
            return receiverName;
        }

        public String getReceiverPhone() {
            return receiverPhone;
        }

        public String getReceiverAddress() {
            return receiverAddress;
        }

        public List<OrderItemRegisterRequest> getOrderItemRegisterRequests() {
            return orderItemRegisterRequests;
        }
    }

    static class OrderItemRegisterRequest {
        Long productId;
        Integer count;

        // Empty Constructor
        public OrderItemRegisterRequest() {}

        public OrderItemRegisterRequest(Long productId, Integer count) {
            this.productId = productId;
            this.count = count;
        }

        // Getter methods
        public Long getProductId() {
            return productId;
        }

        public Integer getCount() {
            return count;
        }
    }
}