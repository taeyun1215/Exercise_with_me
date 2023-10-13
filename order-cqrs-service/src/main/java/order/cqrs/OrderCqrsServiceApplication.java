package order.cqrs;

import global.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@Import({SecurityConfig.class})
@SpringBootApplication
public class OrderCqrsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderCqrsServiceApplication.class, args);
	}

}
