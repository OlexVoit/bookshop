package mate.academy.bookshop.dto.shoppingcart;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ShoppingCartRequestDto {
    @Positive
    private Long bookId;
    @Positive
    private Integer quantity;
}
