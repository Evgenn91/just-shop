package evn.petproject.just_shop.repository;

import evn.petproject.just_shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)//flushAutomatically — JPA сбрасывает изменения в БД перед UPDATE; clearAutomatically — очищает Persistence Context и JPA будет вынуждено сделать SELECT
    @Query("UPDATE Product p SET p.price = :price, p.modifiedBy = :modifiedBy, p.updatedAt = FUNCTION('NOW'), p.version = p.version + 1 WHERE p.id = :id AND p.version = :version")
    int updatePrice(@Param("id") Long id, @Param("price") BigDecimal price, @Param("modifiedBy") String modifiedBy, @Param("version") Long version);
}
