package mate.academy.bookshop.dto.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

@Data
public class CreateBookRequestDto {
    private static final String NOT_BE_NULL = "Can't be null";
    private static final String ISBN_IS_INCORRECT = "isbn is incorrect";

    @NotNull(message = NOT_BE_NULL)
    private String title;

    @NotNull(message = NOT_BE_NULL)
    private String author;

    @NotNull
    @ISBN(message = ISBN_IS_INCORRECT)
    private String isbn;

    @NotNull(message = NOT_BE_NULL)
    @Min(0)
    private BigDecimal price;

    private String description;
    private String coverImage;
}
