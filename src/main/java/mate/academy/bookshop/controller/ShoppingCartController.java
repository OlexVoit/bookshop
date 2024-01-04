package mate.academy.bookshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.bookshop.dto.shoppingcart.AddToCartRequestDto;
import mate.academy.bookshop.dto.shoppingcart.PutCartItemRequestDto;
import mate.academy.bookshop.dto.shoppingcart.ShoppingCartDto;
import mate.academy.bookshop.model.User;
import mate.academy.bookshop.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping Cart", description = "Operations related to shopping cart")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Add books to cart")
    public ShoppingCartDto addToCart(@Valid @RequestBody AddToCartRequestDto requestDto,
                                     Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.addToCart(requestDto, user.getId());
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping
    @Operation(summary = "Get shopping cart")
    public ShoppingCartDto getShoppingCart(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.getShoppingCartByUser(user.getId());
    }

    @PreAuthorize("hasAnyRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a cartItem")
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        shoppingCartService.deleteCartItem(id, user.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/cart-items/{id}")
    @Operation(summary = "Update books quantity",
            description = "Update quantity of a book in the shopping cart")
    public ShoppingCartDto updateById(Authentication authentication,
                                      @PathVariable Long id,
                                      @RequestBody @Valid PutCartItemRequestDto requestDto) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.updateByCartId(user.getId(), id, requestDto);
    }
}
