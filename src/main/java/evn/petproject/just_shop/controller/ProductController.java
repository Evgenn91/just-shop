package evn.petproject.just_shop.controller;

import evn.petproject.just_shop.entity.Product;
import evn.petproject.just_shop.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ---------- CREATE ----------
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product saved = productService.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // ---------- READ (single) ----------
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Optional<Product> product = productService.getById(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ---------- READ (all) ----------
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    // ---------- UPDATE (full) ----------
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        // гарантируем, что обновляется правильный id
        product.setId(id);
        Product updated = productService.update(product);
        return ResponseEntity.ok(updated);
    }

    // ---------- PARTIAL UPDATE (price only) ----------
    @PatchMapping("/{id}/price")
    public ResponseEntity<?> updatePrice(
            @PathVariable Long id,
            @RequestParam BigDecimal newPrice,
            @RequestParam String modifiedBy,
            @RequestParam Long version
    ) {
        boolean success = productService.updatePrice(id, newPrice, modifiedBy, version);
        return success
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.CONFLICT).body("Версия устарела или объект не найден");
    }

    // ---------- DELETE (soft delete) ----------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
