package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.Address;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.validation.AddrDTO;

@Data
@AddrDTO
public class AddressDTO {

    private Long id;

    @NotBlank(message = "Поле не може бути пустим!")
    @Size(max = 100, message = "Код адреси не може містити більше 100 символів")
    private String codAddress;

    @NotBlank(message = "Поле не може бути пустим!")
    @Size(max = 30, message = "Поле не може містити більше 30 символів")
    private String region;

    @NotBlank(message = "Поле не може бути пустим!")
    @Size(max = 30, message = "Поле не може містити більше 30 символів")
    private String city;

    private String addressStr;
    private Integer pointNP;
    private Integer pointUkrPost;

    public static AddressDTO fromEntity(Address address){
        AddressDTO dto = new AddressDTO();
        dto.id = address.getId();
        dto.codAddress = address.getCodAddress();
        dto.region = address.getRegion();
        dto.city = address.getCity();
        dto.addressStr = address.getAddressStr();
        dto.pointNP = address.getPointNP();
        dto.pointUkrPost = address.getPointUkrPost();
        return dto;
    }

    public AddressDTO(){
        id = 0L;
        codAddress = "";
        region = "";
        city = "";
        addressStr = "";
    }
}
