package com.hycun.session.boot.demo.event;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

public class MySessionCreatedEvent extends HttpSessionEvent {

    public MySessionCreatedEvent(HttpSession source) {
        super(source);
    }

    public HttpSession getSession() {
        return (HttpSession)super.getSource();
    }

}
