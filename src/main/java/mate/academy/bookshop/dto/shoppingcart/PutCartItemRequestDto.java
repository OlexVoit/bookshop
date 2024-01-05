package mate.academy.bookshop.dto.shoppingcart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PutCartItemRequestDto {
    private static final String POSITIVE_VALUE = "The value must be positive";
    private static final String NULL_FIELD_MESSAGE = "Can't be null";

    @Positive(message = POSITIVE_VALUE)
    @NotNull(message = NULL_FIELD_MESSAGE)
    private Integer quantity;
}
