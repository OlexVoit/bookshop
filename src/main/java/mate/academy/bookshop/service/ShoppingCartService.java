package mate.academy.bookshop.service;

import mate.academy.bookshop.dto.shoppingcart.AddToCartRequestDto;
import mate.academy.bookshop.dto.shoppingcart.PutCartItemRequestDto;
import mate.academy.bookshop.dto.shoppingcart.ShoppingCartDto;

public interface ShoppingCartService {
    ShoppingCartDto addToCart(AddToCartRequestDto requestDto, Long userID);

    ShoppingCartDto getShoppingCartByUser(Long userId);

    void deleteCartItem(Long cartId, Long userId);

    ShoppingCartDto updateByCartId(Long userId, Long id, PutCartItemRequestDto requestDto);
}
