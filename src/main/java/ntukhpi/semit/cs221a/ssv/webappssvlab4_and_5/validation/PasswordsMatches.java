package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
public @interface PasswordsMatches {
    String message() default "Паролі не співпадають!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
