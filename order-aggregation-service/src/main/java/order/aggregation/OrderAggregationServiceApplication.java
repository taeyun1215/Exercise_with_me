package order.aggregation;

import global.config.SecurityConfig;
import order.aggregation.adapter.in.web.OrderAmountSumController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@Import({SecurityConfig.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class OrderAggregationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderAggregationServiceApplication.class, args);
	}

}
