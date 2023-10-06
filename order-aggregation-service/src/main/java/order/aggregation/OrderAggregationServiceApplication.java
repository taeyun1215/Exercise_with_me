package order.aggregation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("global.common")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class OrderAggregationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderAggregationServiceApplication.class, args);
	}

}
