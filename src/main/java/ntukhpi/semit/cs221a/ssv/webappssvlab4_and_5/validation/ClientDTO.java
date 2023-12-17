package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ClientDTOValidator.class)
public @interface ClientDTO {
    String message() default "Невірні дані";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
