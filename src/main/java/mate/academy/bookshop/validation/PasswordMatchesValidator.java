package mate.academy.bookshop.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mate.academy.bookshop.dto.user.UserRegistrationRequestDto;

public class PasswordMatchesValidator implements ConstraintValidator<FieldMatch,
        UserRegistrationRequestDto> {
    @Override
    public boolean isValid(UserRegistrationRequestDto user,
                           ConstraintValidatorContext constraintValidatorContext) {
        return user.getPassword() != null && user.getPassword().equals(user.getRepeatPassword());
    }
}
