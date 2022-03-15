package ru.tigran.cardcollector.database.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.tigran.cardcollector.database.converters.ListToJsonConverter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_messages")
public class UserMessages {
    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int menuMessageId;

    @Column(nullable = false)
    private int collectIncomeMessageId;

    @Column(nullable = false)
    private int topUsersMessageId;

    @Column(nullable = false)
    private int dailyTaskMessageId;

    @Column(nullable = false)
    private int dailyTaskAlertMessageId;

    @Column(nullable = false)
    private int dailyTaskProgressMessageId;

    @Column(columnDefinition = "longtext")
    @Convert(converter = ListToJsonConverter.class)
    private List<Integer> chatMessages;

    @Column(columnDefinition = "longtext")
    @Convert(converter = ListToJsonConverter.class)
    private List<Integer> chatStickers;

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

    public int getMenuMessageId() {
        return menuMessageId;
    }

    public void setMenuMessageId(int menuMessageId) {
        this.menuMessageId = menuMessageId;
    }

    public int getCollectIncomeMessageId() {
        return collectIncomeMessageId;
    }

    public void setCollectIncomeMessageId(int collectIncomeMessageId) {
        this.collectIncomeMessageId = collectIncomeMessageId;
    }

    public int getTopUsersMessageId() {
        return topUsersMessageId;
    }

    public void setTopUsersMessageId(int topUsersMessageId) {
        this.topUsersMessageId = topUsersMessageId;
    }

    public int getDailyTaskMessageId() {
        return dailyTaskMessageId;
    }

    public void setDailyTaskMessageId(int dailyTaskMessageId) {
        this.dailyTaskMessageId = dailyTaskMessageId;
    }

    public int getDailyTaskAlertMessageId() {
        return dailyTaskAlertMessageId;
    }

    public void setDailyTaskAlertMessageId(int dailyTaskAlertMessageId) {
        this.dailyTaskAlertMessageId = dailyTaskAlertMessageId;
    }

    public int getDailyTaskProgressMessageId() {
        return dailyTaskProgressMessageId;
    }

    public void setDailyTaskProgressMessageId(int dailyTaskProgressMessageId) {
        this.dailyTaskProgressMessageId = dailyTaskProgressMessageId;
    }

    public List<Integer> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<Integer> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public List<Integer> getChatStickers() {
        return chatStickers;
    }

    public void setChatStickers(List<Integer> chatStickers) {
        this.chatStickers = chatStickers;
    }
}
