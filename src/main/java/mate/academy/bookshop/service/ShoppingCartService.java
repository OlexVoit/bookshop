package mate.academy.bookshop.service;

import mate.academy.bookshop.dto.shoppingcart.AddToCartRequestDto;
import mate.academy.bookshop.dto.shoppingcart.ShoppingCartDto;

public interface ShoppingCartService {
    ShoppingCartDto addToCart(AddToCartRequestDto requestDto, Long userID);

    ShoppingCartDto getShoppingCartByUser(Long userId);

    void deleteShoppingCartById(Long cartId, Long userId);

}
