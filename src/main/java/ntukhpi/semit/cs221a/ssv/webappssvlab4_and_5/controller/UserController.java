package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.controller;

import jakarta.validation.Valid;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.dto.UserDTO;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/registration")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(Model model, @Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            model.addAttribute("user", userDTO);
            return "registration";
        }

        userService.saveClient(userDTO);
        return "redirect:/registration?success";
    }

    @GetMapping("login")
    public String login(Model model){
        model.addAttribute("user", new UserDTO());
        return "login";
    }
}
