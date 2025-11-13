package evn.petproject.just_shop.repository;

import evn.petproject.just_shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //don't forget use @Transactional fot this method!!!
    @Modifying
    @Query("UPDATE Product p SET p.price = :price, p.modifiedBy = :modifiedBy, p.updatedAt = CURRENT_TIMESTAMP, p.version = p.version + 1 WHERE p.id = :id AND p.version = :version")
    int updatePrice(@Param("id") Long id, @Param("price") BigDecimal price, @Param("modifiedBy") String modifiedBy, @Param("version") Long version);
}
