package mate.academy.bookshop.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCategoryRequestDto {
    private static final String NOT_BE_NULL = "Can't be null";

    @NotBlank(message = NOT_BE_NULL)
    private String name;

    private String description;
}