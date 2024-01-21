package mate.academy.bookshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookshop.dto.order.OrderItemResponseDto;
import mate.academy.bookshop.dto.order.OrderRequestDto;
import mate.academy.bookshop.dto.order.OrderResponseDto;
import mate.academy.bookshop.dto.order.UpdateOrderRequestDto;
import mate.academy.bookshop.model.User;
import mate.academy.bookshop.service.OrderItemService;
import mate.academy.bookshop.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
@Tag(name = "Order", description = "Operations related to orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}/items")
    @Operation(summary = "Get all items by order id",
            description = "Get all OrderItems for a specific user"
    )
    public List<OrderItemResponseDto> getAllById(Pageable pageable,
                                                 @PathVariable Long id) {
        return orderItemService.getAllById(pageable, id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    @Operation(summary = "Get all orders", description = "The user can receive his orders")
    public List<OrderResponseDto> getAll(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderService.getAll(user.getId());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    @Operation(summary = "Place an order", description = "The user can place an order")
    public OrderResponseDto placeOrder(Authentication authentication,
                                       @RequestBody @Valid OrderRequestDto requestDto
    ) {
        User user = (User) authentication.getPrincipal();
        return orderService.placeOrder(user.getId(), requestDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/{id}")
    @Operation(summary = "Change order status",
            description = "Only the admin can change the order status")
    public OrderResponseDto updateStatus(@PathVariable Long id,
                                         @RequestBody @Valid UpdateOrderRequestDto requestDto) {
        return orderService.updateStatus(id, requestDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}/items/{itemId}")
    @Operation(
            summary = "Get item from order by id",
            description = "Get OrderItem from a specific order"
    )
    public OrderItemResponseDto getItemById(@PathVariable Long id, @PathVariable Long itemId) {
        return orderItemService.getItemById(id, itemId);
    }
}
