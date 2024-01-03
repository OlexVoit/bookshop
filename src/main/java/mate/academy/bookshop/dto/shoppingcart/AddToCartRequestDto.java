package mate.academy.bookshop.dto.shoppingcart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddToCartRequestDto {
    private static final String NOT_BE_NULL = "Can't be null";
    private static final String BE_AT_LEAST_ONE = "There must be at least one";

    @NotNull(message = NOT_BE_NULL)
    private Long bookId;

    @Min(value = 1, message = BE_AT_LEAST_ONE)
    private int quantity;
}
