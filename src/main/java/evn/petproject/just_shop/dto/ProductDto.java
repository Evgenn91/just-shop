package evn.petproject.just_shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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

    @NotBlank(message = "Название не должно быть пустым")
    private String name;
    @NotNull(message = "Цена обязательна")
    private BigDecimal price;
    private boolean deleted;

    @PastOrPresent(message = "Дата создания не может быть в будущем")
    private Instant createdAt;
    @PastOrPresent(message = "Дата создания не может быть в будущем")
    private Instant updatedAt;

    private String createdBy;//GUID
    private String modifiedBy;

    @JsonIgnore
    private Long version;
}
