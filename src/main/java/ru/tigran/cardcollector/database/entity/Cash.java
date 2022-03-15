package ru.tigran.cardcollector.database.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_cash")
public class Cash {
    @Id
    private long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int coins;

    @Column(nullable = false)
    private int gems;

    @Column(nullable = false)
    private int maxCapacity;

    @Column(nullable = false)
    private LocalDateTime lastPayout;

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

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getGems() {
        return gems;
    }

    public void setGems(int gems) {
        this.gems = gems;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public LocalDateTime getLastPayout() {
        return lastPayout;
    }

    public void setLastPayout(LocalDateTime lastPayout) {
        this.lastPayout = lastPayout;
    }
}
