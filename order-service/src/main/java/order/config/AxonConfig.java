package order.config;

import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Autowired
    public void configure(EventProcessingConfigurer eventProcessingConfigurer) {
        eventProcessingConfigurer.usingTrackingEventProcessors();
    }
}
