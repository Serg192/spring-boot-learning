package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.controller;

import jakarta.validation.Valid;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.dto.AddressDTO;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.Address;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.Client;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.User;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.service.AddressService;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.service.ClientService;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;
import java.util.Optional;

@Controller
public class AddressController {

    private final ClientService clientService;
    private final UserService userService;

    private final AddressService addressService;

    public AddressController(ClientService clientService, UserService userService, AddressService addressService) {
        this.clientService = clientService;
        this.userService = userService;
        this.addressService = addressService;
    }

    @GetMapping("/home/account/addresses/new")
    public String showAddressCreationPage(Model model){
        AddressDTO addressDTO = new AddressDTO();
        model.addAttribute("addr", addressDTO);
        return "address/address";
    }

    @PostMapping("/home/account/addresses/new")
    public String addNewAddress(Model model, @Valid @ModelAttribute("addr")AddressDTO addressDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("addr", addressDTO);
            return "address/address";
        }

        Optional<Client> client = getClient();
        if(client.isEmpty()){
            model.addAttribute("errorText", "Cannot retrieve client from database.");
            return "error";
        }

        Address address = Address.fromDTO(addressDTO);
        address.setClient(client.get());
        addressService.saveAddress(address);

        return "redirect:/home/account/addresses";
    }

    @GetMapping("/home/account/addresses/{idAddr}/edit")
    public String showEditAddrPage(@PathVariable Long idAddr, Model model) {

        Optional<Address> address = addressService.getAddressById(idAddr);

        if(address.isEmpty()){
            model.addAttribute("errorText", "Cannot retrieve address with id="+idAddr+" from database.");
            return "error";
        }

        AddressDTO dto = AddressDTO.fromEntity(address.get());
        model.addAttribute("addr", dto);
        return "address/address";
    }

    @PostMapping("/home/account/addresses/{idAddr}/del")
    public String deleteAddress(@PathVariable Long idAddr, Model model){
        Optional<Address> address = addressService.getAddressById(idAddr);

        if(address.isEmpty()){
            model.addAttribute("errorText", "Cannot retrieve address with id="+idAddr+" from database.");
            return "error";
        }

        Optional<Client> client = getClient();

        if(client.isEmpty()){
            model.addAttribute("errorText", "Cannot retrieve client from database.");
            return "error";
        }


        if(Objects.equals(address.get().getClient().getId(), client.get().getId())){
            addressService.deleteAddressById(idAddr);
        }


        return "redirect:/home/account/addresses";
    }

    private String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    private Optional<Client> getClient(){
        User user = userService.findUserByLogin(getUsername()).get();
        return clientService.findClientByUser(user);
    }
}
