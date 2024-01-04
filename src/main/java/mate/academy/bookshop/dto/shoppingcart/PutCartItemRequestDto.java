package mate.academy.bookshop.dto.shoppingcart;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PutCartItemRequestDto {
    @Positive
    private Integer quantity;
}
