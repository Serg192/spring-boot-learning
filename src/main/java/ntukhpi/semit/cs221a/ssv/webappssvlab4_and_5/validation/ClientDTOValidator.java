package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.dto.ClientDTOForUser;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.Client;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.User;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.service.ClientService;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.service.UserService;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.util.Regex;

import java.util.Optional;

public class ClientDTOValidator  implements ConstraintValidator<ClientDTO, Object> {

    private final ClientService clientService;

    public ClientDTOValidator(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        ClientDTOForUser dto = (ClientDTOForUser) o;
        String error = null;

        if(!isValidFullName(dto.getSecondName(), dto.getFirstName())){
            error = "Ім'я та/або прізвище введено невірно!";
            context.buildConstraintViolationWithTemplate(error)
                    .addPropertyNode( "firstName" ).addConstraintViolation();
            context.buildConstraintViolationWithTemplate(error)
                    .addPropertyNode( "secondName" ).addConstraintViolation();
        }

        if(!Regex.emailPattern.matcher(dto.getEmail()).matches()){
            error = "Невірний формат пошти!";
            context.buildConstraintViolationWithTemplate(error)
                    .addPropertyNode( "email" ).addConstraintViolation();
        }

        if(!Regex.phonePattern.matcher(dto.getPhone()).matches()){
            error = "Невірний формат телефону!";
            context.buildConstraintViolationWithTemplate(error)
                    .addPropertyNode( "phone" ).addConstraintViolation();
        }

        Optional<Client> client = clientService.findClientByPhone(dto.getPhone());
        if(client.isPresent()){
            if(client.get().getId() != dto.getId()) {
                error = "Користувач з таким номенор телефону вже зареєстрований!";
                context.buildConstraintViolationWithTemplate(error)
                        .addPropertyNode( "phone" ).addConstraintViolation();
            }
        }

        client = clientService.findClientByEmail(dto.getEmail());
        if(client.isPresent()){
            if(client.get().getId() != dto.getId()){
                error = "Користувач з таким email вже зареєстрований!";
                context.buildConstraintViolationWithTemplate(error)
                        .addPropertyNode( "email" ).addConstraintViolation();
            }
        }

        return error == null;
    }

    private boolean isValidFullName(String lastNameStr, String firstNameStr) {
        return (Regex.englishWordPattern.matcher(lastNameStr).matches() && Regex.englishWordPattern.matcher(firstNameStr).matches())
                || (Regex.ukrainianWordPattern.matcher(lastNameStr).matches() && Regex.ukrainianWordPattern.matcher(firstNameStr).matches());
    }
}
