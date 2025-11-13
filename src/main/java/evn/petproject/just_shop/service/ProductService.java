package evn.petproject.just_shop.service;

import evn.petproject.just_shop.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product create(Product product);

    Optional<Product> getById(Long id);

    List<Product> getAll();

    Product update(Product product);

    void delete(Long id);

    boolean updatePrice(Long id, BigDecimal newPrice, String modifiedBy, Long version);
}
