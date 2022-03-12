package ru.tigran.cardcollector.others;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class ExtendedEmitter extends SseEmitter {
    private long userId;

    public ExtendedEmitter(Long timeout) {
        super(timeout);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
