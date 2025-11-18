package evn.petproject.just_shop.service;

import evn.petproject.just_shop.entity.Product;
import evn.petproject.just_shop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceIntegrationTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void createAndGetById_shouldWorkCorrectly() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(BigDecimal.valueOf(100));

        Product saved = productService.create(product);

        Optional<Product> found = productService.getById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Test Product", found.get().getName());
    }

    @Test
    void getById_nonExistent_shouldReturnEmpty() {
        Optional<Product> found = productService.getById(999L);
        assertTrue(found.isEmpty());
    }

    @Test
    void updatePrice_shouldIncrementVersion() {
        Product product = new Product();
        product.setName("Prod");
        product.setPrice(BigDecimal.valueOf(50));
        Product saved = productService.create(product);

        boolean updated = productService.updatePrice(saved.getId(), BigDecimal.valueOf(60), "user1", saved.getVersion());
        assertTrue(updated);

        Product updatedProduct = productRepository.findById(saved.getId()).orElseThrow();
        assertEquals(BigDecimal.valueOf(60), updatedProduct.getPrice());
        assertEquals(saved.getVersion() + 1, updatedProduct.getVersion());
    }
}
