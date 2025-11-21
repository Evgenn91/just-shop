package evn.petproject.just_shop.repository;

import evn.petproject.just_shop.TestConfig;
import evn.petproject.just_shop.entity.Product;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
@Import(TestConfig.class)
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager em;

    @Test
    void updatePrice_shouldUpdateCorrectly() {
        Product p = new Product();
        p.setName("Test");
        p.setPrice(BigDecimal.valueOf(10));
        p = productRepository.save(p);

        Long oldVersion = p.getVersion();

        int updated = productRepository.updatePrice(
                p.getId(),
                BigDecimal.valueOf(20),
                "user1",
                oldVersion
        );

        assertEquals(1, updated);

        em.clear(); // <-- Ключевой момент!

        Product updatedP = productRepository.findById(p.getId()).orElseThrow();

        assertEquals(0, updatedP.getPrice().compareTo(new BigDecimal("20")));
        assertEquals("user1", updatedP.getModifiedBy());
        assertNotNull(updatedP.getUpdatedAt());
        assertEquals(oldVersion + 1, updatedP.getVersion());
    }
}
