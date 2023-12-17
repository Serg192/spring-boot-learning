package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.controller;

import jakarta.validation.Valid;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.dto.ClientDTOForUser;
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
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class HomePageController {

    private final ClientService clientService;
    private final UserService userService;

    private final AddressService addressService;

    public HomePageController(ClientService clientService, UserService userService, AddressService addressService) {
        this.clientService = clientService;
        this.userService = userService;
        this.addressService = addressService;
    }

    @GetMapping("/home/account/details")
    public String getAccountEdit(Model model){
        ClientDTOForUser client = new ClientDTOForUser();

        Optional<Client> clientInDB = getClient();

        if(clientInDB.isPresent()){
            Client tmp = clientInDB.get();
            client.setEmail(tmp.getEmail());
            client.setPhone(tmp.getPhone());
            client.setGender(tmp.getGender());
            client.setFirstName(tmp.getFirstName());
            client.setSecondName(tmp.getSecondName());
            client.setId(tmp.getId());
        }

        model.addAttribute("client", client);
        return "client/client";
    }

    @PostMapping("/home/account/details")
    public String saveAccountDetails(Model model, @Valid @ModelAttribute("client") ClientDTOForUser clientDTOForUser, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("client", clientDTOForUser);
            return "/client/client";
        }

        Optional<User> user = userService.findUserByLogin(getUsername());

        if(user.isEmpty()){
            model.addAttribute("errorText", "Can't find user with login=" + getUsername());
            return "error";
        }

        Client newClient = Client.fromDTO(clientDTOForUser);
        newClient.setUser(user.get());
        clientService.saveClient(newClient);
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String showHomePage(Model model){

        Optional<Client> client = getClient();

        if(client.isEmpty()){
            return "redirect:/home/account/details";
        }


        model.addAttribute("isVIP", client.get().getIsVIP());
        return "home";
    }

    @GetMapping("/home/account/addresses")
    public String showAddresses(Model model){

        Optional<Client> client = getClient();
        if(client.isEmpty()){
            model.addAttribute("errorText", "Cannot retrieve client from database");
            return "error";
        }

        List<Address> addresses = addressService.getAllAddressesByIdClient(client.get().getId());

        model.addAttribute("aList", addresses);
        model.addAttribute("client", getClient().get());
        return "address/addresses";
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
