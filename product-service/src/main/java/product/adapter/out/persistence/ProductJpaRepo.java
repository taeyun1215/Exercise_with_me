package product.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepo extends JpaRepository<ProductJpaEntity, Long> {

    List<ProductJpaEntity> findByNameContaining(String productNameQuery);

}
