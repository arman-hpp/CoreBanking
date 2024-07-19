package com.bank.web.controllers.users;

import com.bank.web.extensions.errors.ControllerErrorParser;
import com.bank.web.extensions.thymeleaf.Layout;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bank.services.users.AuthenticationService;

@Controller
@RequestMapping("/profile")
@Layout(title = "Profile", value = "layouts/default")
public class ProfileController {
    private final AuthenticationService _authenticationService;

    public ProfileController(AuthenticationService authenticationService) {
        _authenticationService = authenticationService;
    }

    @GetMapping({"/","/index"})
    public String loadForm(Model model) {
        try {
            var userDto = _authenticationService.loadCurrentUser();
            model.addAttribute("userDto", userDto);
        } catch (Exception ex) {
            return "redirect:/profile/index?error=" + ControllerErrorParser.getError(ex);
        }

        return "views/general/profile";
    }
}
