package mate.academy.bookshop.service;

import mate.academy.bookshop.dto.order.OrderRequestDto;
import mate.academy.bookshop.dto.order.OrderResponseDto;
import mate.academy.bookshop.dto.order.UpdateOrderRequestDto;
import java.util.List;

public interface OrderService {
    OrderResponseDto placeAnOrder(Long id, OrderRequestDto requestDto);

    List<OrderResponseDto> getAll(Long id);

    OrderResponseDto updateStatus(Long id, UpdateOrderRequestDto requestDto);
}
