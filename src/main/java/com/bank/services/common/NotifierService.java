package com.bank.services.common;

import org.springframework.stereotype.Service;

@Service
public class NotifierService {
    public void Notify(String message) {
        System.out.println(message);
    }
}
