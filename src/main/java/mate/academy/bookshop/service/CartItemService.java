package mate.academy.bookshop.service;

import mate.academy.bookshop.dto.shoppingcart.CartItemDto;
import mate.academy.bookshop.model.Book;
import mate.academy.bookshop.model.ShoppingCart;

public interface CartItemService {

    CartItemDto updateById(Long id, int quantity);

    void deleteCartItem(Long id);

    void save(int quantity, Book book, ShoppingCart shoppingCart);
}
