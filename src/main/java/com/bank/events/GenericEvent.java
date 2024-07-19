package com.bank.events;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public class GenericEvent<T> extends ApplicationEvent {
    private final T what;
    protected boolean success;

    public GenericEvent(Object source,T what, boolean success) {
        super(source);
        this.what = what;
        this.success = success;
    }
}