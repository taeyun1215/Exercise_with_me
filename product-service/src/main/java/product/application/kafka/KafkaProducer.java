package product.application.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import product.domain.Product;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void loadProduct(String topic, Product product) {
        try {
            String message = objectMapper.writeValueAsString(product);
            log.info("Kafka Producer sent: " + message);
            kafkaTemplate.send(topic, message);
        } catch (JsonProcessingException e) {
            log.error("Failed to process JSON", e);
        }
    }
}
