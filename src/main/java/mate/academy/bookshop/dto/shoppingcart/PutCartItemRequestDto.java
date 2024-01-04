package mate.academy.bookshop.dto.shoppingcart;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PutCartItemRequestDto {
    private static final String POSITIVE_VALUE = "The value must be positive";

    @Positive(message = POSITIVE_VALUE)
    private Integer quantity;
}
