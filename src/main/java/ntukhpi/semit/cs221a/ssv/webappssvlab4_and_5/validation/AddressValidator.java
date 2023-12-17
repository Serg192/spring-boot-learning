package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.dto.AddressDTO;

public class AddressValidator implements ConstraintValidator<AddrDTO, Object> {
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        String error = null;
        AddressDTO addressDTO = (AddressDTO) o;

        if(!canSave(addressDTO.getAddressStr(), addressDTO.getPointNP(), addressDTO.getPointUkrPost())){
            error = "Потрібно вказати щонайменше один варіант!";
            context.buildConstraintViolationWithTemplate(error)
                    .addPropertyNode( "addressStr" ).addConstraintViolation();
            context.buildConstraintViolationWithTemplate(error)
                    .addPropertyNode( "pointNP" ).addConstraintViolation();
            context.buildConstraintViolationWithTemplate(error)
                    .addPropertyNode( "pointUkrPost" ).addConstraintViolation();
        }

        if(!addressDTO.getAddressStr().isEmpty()) {
            if(addressDTO.getAddressStr().trim().length() > 50){
                error = "Поле може містити максимум 50 символів!";
                context.buildConstraintViolationWithTemplate(error)
                        .addPropertyNode( "addressStr" ).addConstraintViolation();
            }
        }

        return error == null;
    }

    private boolean canSave(String addressStr, Integer pointNP, Integer pointUkrPost){
        return !addressStr.trim().isEmpty() ||
                pointNP != null || pointUkrPost != null;
    }
}
