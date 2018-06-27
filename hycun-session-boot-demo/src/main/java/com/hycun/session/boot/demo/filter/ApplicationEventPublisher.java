package com.hycun.session.boot.demo.filter;

import java.util.EventObject;

public class ApplicationEventPublisher extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEventPublisher(Object source) {
        super(source);
    }
}
