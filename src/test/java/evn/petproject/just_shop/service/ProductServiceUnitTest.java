package evn.petproject.just_shop.service;

import evn.petproject.just_shop.entity.Product;
import evn.petproject.just_shop.repository.ProductRepository;
import evn.petproject.just_shop.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceUnitTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void delete_shouldSetDeletedTrue() {
        Product p = new Product();
        p.setDeleted(false);
        when(productRepository.findById(1L)).thenReturn(Optional.of(p));
        when(productRepository.save(any())).thenReturn(p);

        productService.delete(1L);

        assertTrue(p.isDeleted());
        verify(productRepository).save(p);
    }
}
