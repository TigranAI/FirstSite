package ru.tigran.cardcollector.database.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_activity")
public class ChatActivity {
    @Id
    private long id;

    @Column(nullable = false)
    private long messageCount;

    private LocalDateTime lastGiveaway;

    @Column(nullable = false)
    private long messageCountAtLastGiveaway;

    @Column(nullable = false)
    private boolean prizeClaimed;

    @Column(nullable = false)
    private boolean giveawayAvailable;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id", nullable = false)
    private TelegramChat chat;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(long messageCount) {
        this.messageCount = messageCount;
    }

    public LocalDateTime getLastGiveaway() {
        return lastGiveaway;
    }

    public void setLastGiveaway(LocalDateTime lastGiveaway) {
        this.lastGiveaway = lastGiveaway;
    }

    public long getMessageCountAtLastGiveaway() {
        return messageCountAtLastGiveaway;
    }

    public void setMessageCountAtLastGiveaway(long messageCountAtLastGiveaway) {
        this.messageCountAtLastGiveaway = messageCountAtLastGiveaway;
    }

    public boolean isPrizeClaimed() {
        return prizeClaimed;
    }

    public void setPrizeClaimed(boolean prizeClaimed) {
        this.prizeClaimed = prizeClaimed;
    }

    public boolean isGiveawayAvailable() {
        return giveawayAvailable;
    }

    public void setGiveawayAvailable(boolean giveawayAvailable) {
        this.giveawayAvailable = giveawayAvailable;
    }

    public TelegramChat getChat() {
        return chat;
    }

    public void setChat(TelegramChat chat) {
        this.chat = chat;
    }
}
