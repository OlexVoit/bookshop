package mate.academy.bookshop.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.bookshop.dto.shoppingcart.CartItemDto;
import mate.academy.bookshop.exception.EntityNotFoundException;
import mate.academy.bookshop.mapper.CartItemMapper;
import mate.academy.bookshop.model.Book;
import mate.academy.bookshop.model.CartItem;
import mate.academy.bookshop.model.ShoppingCart;
import mate.academy.bookshop.repository.CartItemRepository;
import mate.academy.bookshop.service.CartItemService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    public CartItemDto updateById(Long id, int quantity) {
        CartItem cartItemById = getById(id);
        cartItemById.setQuantity(quantity);
        return cartItemMapper.toDto(cartItemRepository.save(cartItemById));
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public void save(int quantity, Book book, ShoppingCart shoppingCart) {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(quantity);
        cartItem.setBook(book);
        cartItem.setShoppingCart(shoppingCart);
        cartItemRepository.save(cartItem);
    }

    private CartItem getById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId).orElseThrow(
                () -> new EntityNotFoundException("Can not find an item by id: " + cartItemId)
        );
    }
}
