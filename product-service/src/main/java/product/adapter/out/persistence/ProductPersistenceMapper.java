package product.adapter.out.persistence;

import org.springframework.stereotype.Component;
import product.domain.Product;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductPersistenceMapper {

    public ProductJpaEntity mapToJpaEntity(Product product) {
        return product.toJpaEntity();
    }

    public Product mapToDomainEntity(ProductJpaEntity productJpaEntity) {
        return Product.builder()
                .productId(productJpaEntity.getId())
                .name(productJpaEntity.getName())
                .price(productJpaEntity.getPrice())
                .status(productJpaEntity.getStatus())
                .description(productJpaEntity.getDescription())
                .categoryId(productJpaEntity.getCategoryId())
                .userId(productJpaEntity.getUserId())
                .build();
    }

    public List<Product> mapToDomainEntities(List<ProductJpaEntity> productJpaEntities) {
        return productJpaEntities.stream()
                .map(this::mapToDomainEntity)
                .collect(Collectors.toList());
    }

}
