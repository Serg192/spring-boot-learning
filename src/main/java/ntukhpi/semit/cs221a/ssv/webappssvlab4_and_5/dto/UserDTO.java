package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.validation.Login;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.validation.PasswordsMatches;

@Data
@PasswordsMatches
public class UserDTO {

    @Login
    @Size(min = 4, message = "Логін повинен містити щонайменше 4 символи")
    private String login;

    @NotBlank(message = "Поле не може бути пустим!")
    @Size(min = 8, message = "Пароль повинен містити щонайменше 8 символів")
    private String password;

    @NotBlank(message = "Поле не може бути пустим!")
    private String confirmPassword;
}
