package com.bank.web.configs.filters;

import com.bank.exceptions.InvalidCaptchaException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class CaptchaAuthenticationFilter extends GenericFilterBean {
    private final AuthenticationFailureHandler _authenticationFailureHandler;

    public CaptchaAuthenticationFilter(AuthenticationFailureHandler authenticationFailureHandler) {
        _authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    public void doFilter(
            ServletRequest originalRequest,
            ServletResponse originalResponse,
            FilterChain chain) throws IOException, ServletException {

        var request = (HttpServletRequest) originalRequest;
        var response = (HttpServletResponse) originalResponse;

        if(!NeedVerifyCaptcha(request)) {
            chain.doFilter(request, response);
            return;
        }

        if(VerifyCaptcha(request)) {
            chain.doFilter(request, response);
        } else {
            _authenticationFailureHandler.onAuthenticationFailure(request, response,
                    new InvalidCaptchaException("Captcha Error"));
        }
    }

    private Boolean NeedVerifyCaptcha(HttpServletRequest request) {
        return
                request.getServletPath().equalsIgnoreCase("/auth/login") &&
                "POST".equalsIgnoreCase(request.getMethod());
    }

    private Boolean VerifyCaptcha(HttpServletRequest request) {
        var session = request.getSession();
        if (session == null) {
            return false;
        }

        var captcha = (String) session.getAttribute("captcha");
        if (captcha == null) {
            return false;
        }

        session.invalidate();
        var inputCaptcha = request.getParameter("captcha");
        return inputCaptcha != null && inputCaptcha.equals(captcha);
    }
}