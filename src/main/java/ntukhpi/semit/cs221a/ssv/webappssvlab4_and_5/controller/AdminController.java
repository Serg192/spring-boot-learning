package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.controller;

import jakarta.mail.MessagingException;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.Address;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.Client;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.export.ExcelExporter;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.service.AddressService;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.service.ClientService;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.util.EmailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    private final ClientService clientService;
    private final AddressService addressService;

    public AdminController(ClientService clientService, AddressService addressService) {
        this.clientService = clientService;
        this.addressService = addressService;
    }

    @GetMapping("/admin")
    public String showAllClients(Model model){
        model.addAttribute("cList", clientService.getAllClients());
        return "client/clients";
    }

    @GetMapping("/admin/clients/{id}/addresses")
    public String showClientAddresses(@PathVariable Long id, Model model){
        Optional<Client> client = clientService.getClientById(id);

        if(client.isEmpty()){
            model.addAttribute("errorText", "Cannot retrieve client with id="+id+" from database.");
            return "error";
        }


        List<Address> addresses = addressService.getAllAddressesByIdClient(client.get().getId());
        model.addAttribute("aList", addresses);
        model.addAttribute("client", client.get());
        return "address/addresses";
    }

    @PostMapping("/admin/clients/{id}/setVIP")
    public String setVIP(@PathVariable Long id, Model model){
        Optional<Client> client = clientService.getClientById(id);
        if(client.isEmpty()){
            model.addAttribute("errorText", "Cannot retrieve client with id="+id+" from database.");
            return "error";
        }

        client.ifPresent(value -> value.setIsVIP(!value.getIsVIP()));

        return "redirect:/admin";
    }
    @PostMapping("/admin/addresses/send")
    public String sendDataToEmail(@RequestParam("email") String email, Model model){
        System.out.println(email);

        List<Client> clientList = clientService.getAllClients();
        List<Address> addresses = addressService.getAllAddresses();

        Optional<ByteArrayOutputStream> byteArrayOutputStream = ExcelExporter.createXLSXFileFromData(clientList, addresses);

        if(byteArrayOutputStream.isEmpty()){
            model.addAttribute("errorText", "Cannot export data to excel file");
            return "error";
        }

        try {
            EmailSender.sendEmailWithAttachment(byteArrayOutputStream.get().toByteArray(), "sergiystogniy23@gmail.com", email, "Lab5 Stogniy Email Sending TEST");
            return "redirect:/admin";
        } catch (MessagingException e) {
            model.addAttribute("errorText", "Cannot send email");
            return "error";
        }
    }

}

