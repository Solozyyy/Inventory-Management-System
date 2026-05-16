package learnSB.project.Service.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface StrongPassword {
    String message()

    default "Password phai co it nhat 8 ky tu, chu hoa, chu thuong, so va ky tu dac biet";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
