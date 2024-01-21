package mate.academy.bookshop.service;

import java.util.List;
import mate.academy.bookshop.dto.order.OrderRequestDto;
import mate.academy.bookshop.dto.order.OrderResponseDto;
import mate.academy.bookshop.dto.order.UpdateOrderRequestDto;

public interface OrderService {
    OrderResponseDto placeOrder(Long id, OrderRequestDto requestDto);

    List<OrderResponseDto> getAll(Long id);

    OrderResponseDto updateStatus(Long id, UpdateOrderRequestDto requestDto);
}
