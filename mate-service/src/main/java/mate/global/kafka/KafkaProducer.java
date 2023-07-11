package mate.global.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mate.adapter.in.request.CreateMatePostRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, CreateMatePostRequest createMatePostRequest) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";

        try {
            jsonInString = mapper.writeValueAsString(createMatePostRequest);
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
        }

        log.info("Kafka Producer sent: " + jsonInString);
        kafkaTemplate.send(topic, jsonInString);
    }
}
