package evn.petproject.just_shop.repository;

import evn.petproject.just_shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
