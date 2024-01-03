package mate.academy.bookshop.dto.shoppingcart;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;
import lombok.Data;
import mate.academy.bookshop.model.CartItem;
import mate.academy.bookshop.model.User;

@Data
public class CartItemDto {
//    private static final String NOT_BE_NULL = "Can't be null";
//
//    @NotBlank(message = NOT_BE_NULL)
//    private User user;
//    @NotBlank(message = NOT_BE_NULL)
//    private Set<CartItem> cartItems;

    private Long id;
    private Long bookId;
    private String bookTitle;
    private int quantity;
}
