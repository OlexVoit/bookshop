package mate.academy.bookshop.dto.user;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserLoginRequestDto(
        @NotBlank
        @Length(min = 6, max = 24)
        String email,
        @NotBlank
        @Length(min = 6, max = 24)
        String password
) {
}
