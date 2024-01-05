package mate.academy.bookshop.service.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import mate.academy.bookshop.dto.shoppingcart.AddToCartRequestDto;
import mate.academy.bookshop.dto.shoppingcart.PutCartItemRequestDto;
import mate.academy.bookshop.dto.shoppingcart.ShoppingCartDto;
import mate.academy.bookshop.exception.EntityNotFoundException;
import mate.academy.bookshop.exception.ResourceNotFoundException;
import mate.academy.bookshop.exception.UnauthorizedOperationException;
import mate.academy.bookshop.mapper.ShoppingCartMapper;
import mate.academy.bookshop.model.Book;
import mate.academy.bookshop.model.ShoppingCart;
import mate.academy.bookshop.model.User;
import mate.academy.bookshop.repository.BookRepository;
import mate.academy.bookshop.repository.CartItemRepository;
import mate.academy.bookshop.repository.ShoppingCartRepository;
import mate.academy.bookshop.repository.UserRepository;
import mate.academy.bookshop.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemServiceImpl cartItemService;
    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public ShoppingCartDto updateByCartId(Long userId,Long id, PutCartItemRequestDto requestDto) {
        cartItemService.updateById(id, requestDto.getQuantity());
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't find"
                                + " shopping cart by user's id: " + userId)
                );
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto getShoppingCartByUser(Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository
                .findByUserId(userId).orElseThrow(() ->
                        new ResourceNotFoundException("User does not have a shopping cart"
                                + userId));
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public void deleteCartItem(Long cartId, Long userId) {
        shoppingCartRepository.findById(cartId)
                .ifPresent(shoppingCarts -> {
                    if (Objects.equals(shoppingCarts.getUser().getId(), userId)) {
                        cartItemRepository.deleteById(userId);
                    } else {
                        throw new UnauthorizedOperationException(
                                "Can't find cart item by id %s for user with id %s"
                                        .formatted(cartId, userId));
                    }
                });
        throw new ResourceNotFoundException("The cart item was not found or deleted. Id: "
                + cartId);
    }

    @Transactional
    @Override
    public ShoppingCartDto addToCart(AddToCartRequestDto requestDto, Long userId) {
        Book book = bookRepository.findById(requestDto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Can't find the book by id: "
                        + requestDto.getBookId()));

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Can't find the user by id: "
                                + userId));

        ShoppingCart shoppingCartFromDb =
                shoppingCartRepository.findByUser_Id(userId).orElseGet(() -> {
                    ShoppingCart shoppingCart = new ShoppingCart();
                    shoppingCart.setUser(user);
                    shoppingCartRepository.save(shoppingCart);
                    return shoppingCart;
                });

        cartItemService.save(requestDto.getQuantity(), book, shoppingCartFromDb);
        return shoppingCartMapper.toDto(shoppingCartFromDb);
    }
}
