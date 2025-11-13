package evn.petproject.just_shop.service.impl;

import evn.petproject.just_shop.entity.Product;
import evn.petproject.just_shop.repository.ProductRepository;
import evn.petproject.just_shop.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id)
                .filter(p -> !p.isDeleted());
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public boolean updatePrice(Long id, BigDecimal newPrice, String modifiedBy, Long version) {
        int updated = productRepository.updatePrice(id, newPrice, modifiedBy, version);
        return updated > 0;
    }

    @Override
    public void delete(Long id) {
        productRepository.findById(id).ifPresent(product -> {
            product.setDeleted(true);
            productRepository.save(product);
        });
    }
}
