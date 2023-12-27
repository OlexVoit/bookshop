package mate.academy.bookshop.dto.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    private static final String NOT_BE_NULL = "Don't be null";
    private static final String BE_11_TO_13_CHARACTERS = "Must contain 11 to 13 characters long";

    @NotNull(message = NOT_BE_NULL)
    private String title;

    @NotNull(message = NOT_BE_NULL)
    private String author;

    @NotNull
    @Size(min = 11, max = 13, message = BE_11_TO_13_CHARACTERS)
    private String isbn;

    @NotNull(message = NOT_BE_NULL)
    @Min(0)
    private BigDecimal price;

    private String description;
    private String coverImage;
}
