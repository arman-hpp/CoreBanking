package com.bank.web.controllers.users;

import com.bank.repos.users.UserRepository;
import com.bank.services.users.CaptchaService;
import com.bank.web.extensions.thymeleaf.Layout;
import com.bank.dtos.users.UserLoginInputDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(("/auth"))
@Layout(title = "Login", value = "layouts/bootstrap")
public class AuthenticationController {
    private final CaptchaService _captchaService;
    private final UserRepository userRepository;

    public AuthenticationController(CaptchaService captchaService,
                                    UserRepository userRepository) {
        _captchaService = captchaService;
        this.userRepository = userRepository;
    }

    @GetMapping( "/login")
    public String loginForm(@RequestParam(required = false) String username,
                            HttpServletRequest request,
                            Model model) {
        var user = new UserLoginInputDto();

        var captcha = _captchaService.createCaptcha();
        user.setCaptchaImage(captcha.getEncodedCaptcha());

        var session = request.getSession();
        if(session != null) {
            session.setAttribute("captcha", captcha.getAnswer());
        }

        if(username == null) {
            model.addAttribute("loginInputDto", user);
        } else {
            user.setUsername(username);
            model.addAttribute("loginInputDto", user);
        }

        return "views/public/login";
    }
}
