import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import product.ProductServiceApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest(classes = ProductServiceApplication.class)
public class ProductSearchControllerTest {

    private final String baseUrl = "http://localhost:8083/product-service/products/search?searchQuery=product";

    @Test
    public void testSequentialSearchRequests() {
        int numberOfRequests = 100;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numberOfRequests; i++) {
            new RestTemplate().getForObject(baseUrl, String.class);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time for sequential requests: " + (endTime - startTime) + " ms");
    }

    @Test
    public void testConcurrentSearchRequestsAsync() throws ExecutionException, InterruptedException {
        int numberOfRequests = 100;
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numberOfRequests; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                new RestTemplate().getForObject(baseUrl, String.class);
            });
            futures.add(future);
        }

        // 모든 비동기 작업이 완료될 때까지 기다립니다.
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time for all asynchronous requests: " + (endTime - startTime) + " ms");
    }
}