package mate.academy.bookshop.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDto {
    @NotNull(message = "Can't be null")
    @Min(5)
    private String shippingAddress;
}
