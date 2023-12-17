package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.User;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.enums.Gender;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.validation.ClientDTO;

@Data
@ClientDTO
public class ClientDTOForUser {

    private Long id;

    private User user;

    @NotBlank(message = "Поле не може бути пустим!")
    @Size(max = 100, message = "Ім'я не повинно містити більше 100 символів")
    private String firstName;

    @NotBlank(message = "Поле не може бути пустим!")
    @Size(max = 100, message = "Прізвище не повинно містити більше 100 символів")
    private String secondName;

    @NotBlank(message = "Поле не може бути пустим!")
    private String phone;

    @NotBlank(message = "Поле не може бути пустим!")
    private String email;

    private Gender gender;

    public ClientDTOForUser(){
        id = 0L;
        firstName = "";
        secondName = "";
        email = "";
        phone = "";
    }
}
