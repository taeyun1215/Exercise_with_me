package stock.application.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import stock.application.port.in.IncreaseStockUseCase;
import stock.application.port.in.ReduceStockUseCase;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final ReduceStockUseCase reduceStockUseCase;
    private final IncreaseStockUseCase increaseStockUseCase;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "reduce-stock")
    public void reduceStock(String kafkaMessage) {
        log.info("Kafka Message: ->" + kafkaMessage);

        try {
            OrderItemDto orderItemDto = objectMapper.readValue(kafkaMessage, OrderItemDto.class);
            reduceStockUseCase.reduceStock(orderItemDto);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }

    @KafkaListener(topics = "increase-stock")
    public void increaseStock(String kafkaMessage) {
        log.info("Kafka Message: ->" + kafkaMessage);

        try {
            OrderItemDto orderItemDto = objectMapper.readValue(kafkaMessage, OrderItemDto.class);
            increaseStockUseCase.increaseStock(orderItemDto);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }

}
