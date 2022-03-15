package ru.tigran.cardcollector.database.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "chat_roulette")
public class ChatRoulette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private TelegramChat group;

    @Column(nullable = false)
    private int messageId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_roulette_id", nullable = false)
    private List<UserSticker> bets;

    @Column(nullable = false)
    private boolean isStarted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public TelegramChat getGroup() {
        return group;
    }

    public void setGroup(TelegramChat group) {
        this.group = group;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public List<UserSticker> getBets() {
        return bets;
    }

    public void setBets(List<UserSticker> bets) {
        this.bets = bets;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }
}
