package mate.academy.bookshop.service;

import java.util.List;
import mate.academy.bookshop.dto.order.OrderItemResponseDto;

public interface OrderItemService {

    List<OrderItemResponseDto> getAllById(Long id);

    OrderItemResponseDto getItemById(Long id, Long itemId);
}
