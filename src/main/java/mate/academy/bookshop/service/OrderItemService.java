package mate.academy.bookshop.service;

import mate.academy.bookshop.dto.order.OrderItemResponseDto;

import java.util.List;

public interface OrderItemService {

    List<OrderItemResponseDto> getAllById(Long id);

    OrderItemResponseDto getItemById(Long id, Long itemId);
}