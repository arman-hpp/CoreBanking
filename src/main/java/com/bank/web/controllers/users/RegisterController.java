package com.bank.web.controllers.users;

import com.bank.web.extensions.errors.ControllerErrorParser;
import com.bank.web.extensions.thymeleaf.Layout;
import com.bank.dtos.users.UserRegisterInputDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.bank.services.users.UserService;
import com.bank.utils.web.RequestParamsBuilder;

@Controller
@RequestMapping(("/register"))
@Layout(title = "Register", value = "layouts/public")
public class RegisterController {
    private final UserService _userService;

    public RegisterController(UserService _userService) {
        this._userService = _userService;
    }

    @GetMapping({"/","/index"})
    public String registerForm(@RequestParam(required = false) String username, Model model) {
        if(username == null) {
            model.addAttribute("userRegisterInputDto", new UserRegisterInputDto());
        } else {
            model.addAttribute("userRegisterInputDto", new UserRegisterInputDto(username));
        }

        return "views/public/register";
    }

    @PostMapping("/addUser")
    public String registerSubmit(@ModelAttribute UserRegisterInputDto userRegisterInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/register/index?error=" + ControllerErrorParser.getError(bindingResult);
        }

        try {
            _userService.register(userRegisterInputDto);

            return "redirect:/auth/login";
        } catch (Exception ex) {
            return new RequestParamsBuilder("redirect:/register/index")
                    .Add("username", userRegisterInputDto.getUsername())
                    .Add("error", ControllerErrorParser.getError(ex))
                    .toString();
        }
    }
}
