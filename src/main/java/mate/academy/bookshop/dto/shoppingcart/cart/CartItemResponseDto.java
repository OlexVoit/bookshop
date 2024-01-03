package mate.academy.bookshop.dto.shoppingcart.cart;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import mate.academy.bookshop.model.Book;
import mate.academy.bookshop.model.ShoppingCart;

@Data
public class CartItemResponseDto {
    private static final String NOT_BE_NULL = "Can't be null";
    @NotBlank(message = NOT_BE_NULL)
    private ShoppingCart shoppingCart;

    @NotBlank(message = NOT_BE_NULL)
    private Book book;

    @NotBlank(message = NOT_BE_NULL)
    private int quantity;

}
