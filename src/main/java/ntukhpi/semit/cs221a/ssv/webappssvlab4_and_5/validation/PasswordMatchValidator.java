package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.dto.UserDTO;

public class PasswordMatchValidator implements ConstraintValidator<PasswordsMatches, Object> {
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserDTO user = (UserDTO) obj;
        boolean isValid = user.getPassword().equals(user.getConfirmPassword());
        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode( "confirmPassword" ).addConstraintViolation();
        }
        return isValid;
    }
}
