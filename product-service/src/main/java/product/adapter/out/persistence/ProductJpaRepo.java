package product.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepo extends JpaRepository<ProductJpaEntity, Long> {
}
