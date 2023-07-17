package product.adapter.out.persistence;

import global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import product.application.port.out.LoadProductPort;
import product.application.port.out.SaveProductPort;
import product.domain.Product;

@RequiredArgsConstructor
@PersistenceAdapter
public class ProductPersistenceAdapter implements
        SaveProductPort, LoadProductPort {

    private final ProductJpaRepo productJpaRepo;
    private final ProductPersistenceMapper productPersistenceMapper;

    @Override
    public void saveProduct(Product product) {
        productJpaRepo.save(productPersistenceMapper.mapToJpaEntity(product));
    }

    @Override
    public Product loadProduct(Long productId) {
        return productPersistenceMapper.mapToDomainEntity(productJpaRepo.findById(productId).get());
    }

}
