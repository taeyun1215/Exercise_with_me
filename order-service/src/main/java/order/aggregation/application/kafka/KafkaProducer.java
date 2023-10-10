package order.aggregation.application.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.domain.OrderItem;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void checkProductAvailability(String topic, Long productId) {
        try {
            String message = objectMapper.writeValueAsString(productId);
            log.info("Kafka Producer sent: " + message);
            kafkaTemplate.send(topic, message);
        } catch (JsonProcessingException e) {
            log.error("Failed to process JSON", e);
        }
    }

    public void reduceStock(String topic, OrderItem orderItem) {
        try {
            String message = objectMapper.writeValueAsString(orderItem);
            log.info("Kafka Producer sent: " + message);
            kafkaTemplate.send(topic, message);
        } catch (JsonProcessingException e) {
            log.error("Failed to process JSON", e);
        }
    }

    public void increaseStock(String topic, OrderItem orderItem) {
        try {
            String message = objectMapper.writeValueAsString(orderItem);
            log.info("Kafka Producer sent: " + message);
            kafkaTemplate.send(topic, message);
        } catch (JsonProcessingException e) {
            log.error("Failed to process JSON", e);
        }
    }

}
