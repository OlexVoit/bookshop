package mate.academy.bookshop.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.bookshop.dto.shoppingcart.ShoppingCartDto;
import mate.academy.bookshop.dto.shoppingcart.AddToCartRequestDto;
import mate.academy.bookshop.model.User;
import mate.academy.bookshop.service.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping Cart", description = "Operations related to shopping cart")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping
    public ShoppingCartDto addToCart(@RequestBody @Valid AddToCartRequestDto requestDto,
                                     Authentication authentication
    ) {
        User user = (User)authentication.getPrincipal();
        return shoppingCartService.addToCart(requestDto, user.getId());

    }

}
