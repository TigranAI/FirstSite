package ru.tigran.cardcollector.others;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class ExtendedEmitter extends SseEmitter {
    private Long userId;

    public ExtendedEmitter(Long timeout) {
        super(timeout);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
