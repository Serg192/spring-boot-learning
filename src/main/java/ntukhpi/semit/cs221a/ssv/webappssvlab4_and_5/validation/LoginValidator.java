package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.User;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.service.UserService;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.util.Regex;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class LoginValidator implements ConstraintValidator<Login, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        String error = null;

        Optional<User> user = userService.findUserByLogin(login);
        if(user.isPresent())
            error = "Користувач з таким ім'ям вже існує!";

        if(error != null) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(error).addConstraintViolation();
            return false;
        }

        return true;
    }
}
