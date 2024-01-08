package mate.academy.bookshop.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mate.academy.bookshop.model.Order;

@Data
public class UpdateOrderRequestDto {
    @NotNull
    private Order.Status status;
}