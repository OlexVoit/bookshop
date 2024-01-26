package mate.academy.bookshop.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.bookshop.dto.order.OrderRequestDto;
import mate.academy.bookshop.dto.order.OrderResponseDto;
import mate.academy.bookshop.dto.order.UpdateOrderRequestDto;
import mate.academy.bookshop.exception.EntityNotFoundException;
import mate.academy.bookshop.mapper.OrderMapper;
import mate.academy.bookshop.model.CartItem;
import mate.academy.bookshop.model.Order;
import mate.academy.bookshop.model.OrderItem;
import mate.academy.bookshop.model.ShoppingCart;
import mate.academy.bookshop.model.User;
import mate.academy.bookshop.repository.CartItemRepository;
import mate.academy.bookshop.repository.OrderItemRepository;
import mate.academy.bookshop.repository.OrderRepository;
import mate.academy.bookshop.repository.ShoppingCartRepository;
import mate.academy.bookshop.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public OrderResponseDto placeOrder(Long id, OrderRequestDto requestDto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find cart by user id: " + id)
        );
        Order order = createOrder(id, shoppingCart, requestDto);
        Order savedOrder = orderRepository.save(order);
        Set<OrderItem> orderItems = getOrderItems(shoppingCart);
        savedOrder.setOrderItems(orderItems);
        orderItems.forEach(oi -> oi.setOrder(order));
        orderItemRepository.saveAll(orderItems);
        cartItemRepository.deleteAll(shoppingCart.getCartItems());
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public List<OrderResponseDto> getAll(Long id, Pageable pageable) {
        List<Order> orderList = orderRepository.findAllById(id, pageable);
        return orderList.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderResponseDto updateStatus(Long id, UpdateOrderRequestDto requestDto) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find order by id: " + id)
        );
        order.setStatus(requestDto.getStatus());
        return orderMapper.toDto(orderRepository.save(order));
    }

    private Order createOrder(Long id, ShoppingCart shoppingCart, OrderRequestDto requestDto) {
        Order order = new Order();
        User user = new User();
        user.setId(id);
        order.setUser(user);
        order.setStatus(Order.Status.PROCESSING);
        order.setShippingAddress(requestDto.getShippingAddress());
        order.setTotal(
                shoppingCart.getCartItems().stream()
                        .map(ci -> ci.getBook()
                                .getPrice()
                                .multiply(BigDecimal.valueOf(ci.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    private Set<OrderItem> getOrderItems(ShoppingCart shoppingCart) {
        Set<CartItem> cartItems = shoppingCart.getCartItems();
        return cartItems.stream()
                .map(this::convertToOrderItem)
                .collect(Collectors.toSet());

    }

    private OrderItem convertToOrderItem(CartItem cartItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setBook(cartItem.getBook());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setPrice(cartItem.getBook().getPrice());
        return orderItem;
    }
}
