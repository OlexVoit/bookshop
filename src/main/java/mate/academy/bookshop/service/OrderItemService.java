package mate.academy.bookshop.service;

import java.util.List;
import mate.academy.bookshop.dto.order.OrderItemResponseDto;
import org.springframework.data.domain.Pageable;

public interface OrderItemService {

    List<OrderItemResponseDto> getAllById(Pageable pageable, Long id);

    OrderItemResponseDto getItemById(Long id, Long itemId);
}
