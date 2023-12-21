package mate.academy.bookshop.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import mate.academy.bookshop.dto.user.UserRegistrationRequestDto;

public class PasswordMatchesValidator implements ConstraintValidator<FieldMatch,
        UserRegistrationRequestDto> {
    @Override
    public boolean isValid(UserRegistrationRequestDto user,
                           ConstraintValidatorContext constraintValidatorContext) {
        return user.getPassword() != null
                && Objects.equals(user.getPassword(), user.getRepeatPassword());
    }
}
