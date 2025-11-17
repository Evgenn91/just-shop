package evn.petproject.just_shop.controller;

import evn.petproject.just_shop.dto.ProductDto;
import evn.petproject.just_shop.entity.Product;
import evn.petproject.just_shop.mapper.ProductMapper;
import evn.petproject.just_shop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper mapper;

    public ProductController(ProductService productService, ProductMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    // ---------- CREATE ----------
    @PostMapping
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto dto) {
        Product entity = mapper.toEntity(dto);
        Product saved = productService.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toDto(saved));
    }

    // ---------- READ (single) ----------
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
        return productService.getById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ---------- READ (all) ----------
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> products = productService.getAll()
                .stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(products);
    }
    // ---------- UPDATE (full) ----------
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id,@Valid @RequestBody ProductDto dto) {
        dto.setId(id);
        Product updated = productService.update(mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toDto(updated));
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
