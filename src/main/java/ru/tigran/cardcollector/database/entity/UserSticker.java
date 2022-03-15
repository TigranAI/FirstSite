package ru.tigran.cardcollector.database.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_stickers")
public class UserSticker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sticker_id", referencedColumnName = "id", nullable = false)
    private Sticker sticker;

    @Column(nullable = false)
    private int count;

    @Column(nullable = false)
    private LocalDateTime payout;

    private LocalDateTime givePrizeDate;

    private LocalDateTime lastUsage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sticker getSticker() {
        return sticker;
    }

    public void setSticker(Sticker sticker) {
        this.sticker = sticker;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDateTime getPayout() {
        return payout;
    }

    public void setPayout(LocalDateTime payout) {
        this.payout = payout;
    }

    public LocalDateTime getGivePrizeDate() {
        return givePrizeDate;
    }

    public void setGivePrizeDate(LocalDateTime givePrizeDate) {
        this.givePrizeDate = givePrizeDate;
    }

    public LocalDateTime getLastUsage() {
        return lastUsage;
    }

    public void setLastUsage(LocalDateTime lastUsage) {
        this.lastUsage = lastUsage;
    }
}
