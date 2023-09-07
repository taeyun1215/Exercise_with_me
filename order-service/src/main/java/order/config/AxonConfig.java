package order.config;

import com.thoughtworks.xstream.XStream;
import global.command.ReduceStockCommand;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

//    @Autowired
//    public void configure(EventProcessingConfigurer eventProcessingConfigurer) {
//        eventProcessingConfigurer.usingTrackingEventProcessors();
//    }

    private XStream xstream;

    public AxonConfig(XStream xstream) {
        this.xstream = xstream;
    }

    public void configure() {
        // ReduceStockCommand 클래스를 XStream의 허용 목록에 추가합니다.
        xstream.allowTypes(new Class[]{ReduceStockCommand.class});
    }
    
}