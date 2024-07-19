package com.bank.exceptions;

import org.springframework.security.authentication.InsufficientAuthenticationException;

public class InvalidCaptchaException extends InsufficientAuthenticationException {
    public InvalidCaptchaException(String msg) {
        super(msg);
    }

    public InvalidCaptchaException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
