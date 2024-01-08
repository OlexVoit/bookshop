package mate.academy.bookshop.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookshop.dto.order.OrderItemResponseDto;
import mate.academy.bookshop.exception.EntityNotFoundException;
import mate.academy.bookshop.mapper.OrderItemMapper;
import mate.academy.bookshop.model.OrderItem;
import mate.academy.bookshop.repository.OrderItemRepository;
import mate.academy.bookshop.service.OrderItemService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemResponseDto> getAllById(Long id) {
        List<OrderItem> items = orderItemRepository.findAllById(id);
        return items.stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemResponseDto getItemById(Long id, Long itemId) {
        OrderItem orderItem = orderItemRepository.findById(id, itemId).orElseThrow(
                () -> new EntityNotFoundException("Can't find item by id: " + id)
        );
        return orderItemMapper.toDto(orderItem);
    }
}