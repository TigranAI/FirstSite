package ru.tigran.cardcollector.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_level")
public class UserLevel {
    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private long currentExp;

    @Column(nullable = false)
    private long totalExp;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(long currentExp) {
        this.currentExp = currentExp;
    }

    public long getTotalExp() {
        return totalExp;
    }

    public void setTotalExp(long totalExp) {
        this.totalExp = totalExp;
    }
}
