package mate.academy.bookshop.dto.shoppingcart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddToCartRequestDto {
    private static final String NULL_FIELD_MESSAGE = "Can't be null";
    private static final String POSITIVE_VALUE = "The value must be positive";

    @NotNull(message = NULL_FIELD_MESSAGE)
    private Long bookId;

    @Positive(message = POSITIVE_VALUE)
    private int quantity;
}
