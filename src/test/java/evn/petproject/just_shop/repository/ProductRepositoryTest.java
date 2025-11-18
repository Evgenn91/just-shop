package evn.petproject.just_shop.repository;

import evn.petproject.just_shop.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void updatePrice_shouldUpdateCorrectly() {
        Product p = new Product();
        p.setName("Test");
        p.setPrice(BigDecimal.valueOf(10));
        p = productRepository.save(p);

        int updated = productRepository.updatePrice(p.getId(), BigDecimal.valueOf(20), "user1", p.getVersion());
        assertEquals(1, updated);

        Product updatedP = productRepository.findById(p.getId()).orElseThrow();
        assertEquals(BigDecimal.valueOf(20), updatedP.getPrice());
    }
}
