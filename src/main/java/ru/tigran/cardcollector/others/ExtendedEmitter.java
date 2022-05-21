package ru.tigran.cardcollector.others;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpSession;

public class ExtendedEmitter extends SseEmitter {
    private HttpSession session;

    public ExtendedEmitter(Long timeout) {
        super(timeout);
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
}
