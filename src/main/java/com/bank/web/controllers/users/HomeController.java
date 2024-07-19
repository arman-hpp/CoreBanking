package com.bank.web.controllers.users;

import com.bank.web.extensions.thymeleaf.Layout;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
@Layout(title = "Home", value = "layouts/default")
public class HomeController {
    @GetMapping({"/", "index"})
    public String home() {
        return "views/general/home";
    }
}
