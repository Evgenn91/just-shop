package evn.petproject.just_shop.mapper;

import evn.petproject.just_shop.dto.ProductDto;
import evn.petproject.just_shop.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto toDto(Product entity) {
        if (entity == null) return null;

        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setVersion(entity.getVersion());
        return dto;
    }

    public Product toEntity(ProductDto dto) {
        if (dto == null) return null;

        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        // поля createdAt/updatedAt управляются JPA, не заполняем вручную
        entity.setVersion(dto.getVersion());
        return entity;
    }
}
