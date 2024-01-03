package mate.academy.bookshop.dto.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateBookRequestDto {
    private static final String NOT_BE_NULL = "Can't be null";
    private static final String ISBN_IS_INCORRECT = "Must contain from 9 to 13 characters";

    @NotNull(message = NOT_BE_NULL)
    private String title;

    @NotNull(message = NOT_BE_NULL)
    private String author;

    @NotNull
    @Length(min = 9, max = 13, message = ISBN_IS_INCORRECT)
    private String isbn;

    @NotNull(message = NOT_BE_NULL)
    @Min(0)
    private BigDecimal price;

    private String description;
    private String coverImage;

    @NotNull(message = NOT_BE_NULL)
    private Set<Long> categories;
}
