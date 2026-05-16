package learnSB.project.Service.validator;

import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import learnSB.project.DTO.Request.RegisterRequestDTO;
import learnSB.project.Service.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterRequestDTO> {

    private final UserService userService;

    @Override
    public boolean isValid(RegisterRequestDTO value, ConstraintValidatorContext context) {

        // check pass
        String password = value.getPassword();
        String confirmPassword = value.getConfirmPassword();
        if (!password.equals(confirmPassword)) {

            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate("Confirm password không khớp")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();

            return false;
        }

        if (userService.isUserNameExists(value.getUserName())) {
            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate("UserName đã tồn tại")
                    .addPropertyNode("userName")
                    .addConstraintViolation();

            return false;
        }
        return true;
    }

}
