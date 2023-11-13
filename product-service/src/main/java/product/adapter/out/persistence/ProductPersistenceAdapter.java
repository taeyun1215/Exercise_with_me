package product.adapter.out.persistence;

import global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import product.application.port.in.query.SearchProductsByQuery;
import product.application.port.out.LoadProductPort;
import product.application.port.out.SaveProductPort;
import product.domain.Product;

import java.util.List;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements
        SaveProductPort, LoadProductPort {

    private final ProductJpaRepo productJpaRepo;
    private final ProductPersistenceMapper productPersistenceMapper;

    @Cacheable(key = "'all'")
    public List<Product> findAllProducts() {
        List<ProductJpaEntity> entities = productJpaRepo.findAll();
        return productPersistenceMapper.mapToDomainEntities(entities);
    }

    @Override
    @CachePut(key = "#product.id")
    @CacheEvict(key = "'all'", condition = "#product != null")
    public void saveProduct(Product product) {
        ProductJpaEntity savedEntity = productJpaRepo.save(productPersistenceMapper.mapToJpaEntity(product));
        log.info("Saved product {}", savedEntity);
    }

    @Override
    @Cacheable(key = "#productId", unless = "#result == null")
    public Product loadProduct(Long productId) {
        return productPersistenceMapper.mapToDomainEntity(productJpaRepo.findById(productId).orElse(null));
    }

    @CachePut(key = "#product.id")
    @CacheEvict(key = "'all'")
    public Product updateProduct(Product product) {
        ProductJpaEntity updatedEntity = productJpaRepo.save(productPersistenceMapper.mapToJpaEntity(product));
        log.info("Updated product {}", updatedEntity);
        return productPersistenceMapper.mapToDomainEntity(updatedEntity);
    }

    @Caching(evict = {
            @CacheEvict(key = "#productId"),
            @CacheEvict(key = "'all'")
    })
    public void deleteProduct(Long productId) {
        productJpaRepo.deleteById(productId);
        log.info("Deleted product with ID {}", productId);
    }

    @Override
    public List<Product> loadProductByQuery(SearchProductsByQuery query) {
        return productPersistenceMapper.mapToDomainEntities(productJpaRepo.findByNameContaining(query.getProductName()));
    }
}
