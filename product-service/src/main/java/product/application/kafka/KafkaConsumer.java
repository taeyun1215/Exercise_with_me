package product.application.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import product.application.port.out.LoadProductPort;
import product.domain.Product;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final LoadProductPort loadProductPort;
    private final KafkaProducer kafkaProducer;

    @KafkaListener(topics = "check-product-topic")
    public void checkProductAvailability(String kafkaMessage) {
        log.info("Kafka Message: ->" + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        Product product = loadProductPort.loadProduct((Long) map.get("productId"));
        kafkaProducer.loadProduct("order-topic", product);
    }

}
