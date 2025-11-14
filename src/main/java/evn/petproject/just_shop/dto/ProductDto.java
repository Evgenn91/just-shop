package evn.petproject.just_shop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@Setter
@Getter
public class ProductDto {
    private Long id;

    private String name;
    private BigDecimal price;
    private boolean deleted;

    private Instant createdAt;
    private Instant updatedAt;

    private String createdBy;//GUID
    private String modifiedBy;

    private Long version;
}
