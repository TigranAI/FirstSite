package ru.tigran.cardcollector.database.entity;

import ru.tigran.cardcollector.database.converters.ListToJsonConverter;

import javax.persistence.*;
import java.util.List;

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
    private Integer menuMessageId;

    @Column(nullable = false)
    private Integer collectIncomeMessageId;

    @Column(nullable = false)
    private Integer topUsersMessageId;

    @Column(nullable = false)
    private Integer dailyTaskMessageId;

    @Column(nullable = false)
    private Integer dailyTaskAlertMessageId;

    @Column(nullable = false)
    private Integer dailyTaskProgressMessageId;

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

    public Integer getMenuMessageId() {
        return menuMessageId;
    }

    public void setMenuMessageId(Integer menuMessageId) {
        this.menuMessageId = menuMessageId;
    }

    public Integer getCollectIncomeMessageId() {
        return collectIncomeMessageId;
    }

    public void setCollectIncomeMessageId(Integer collectIncomeMessageId) {
        this.collectIncomeMessageId = collectIncomeMessageId;
    }

    public Integer getTopUsersMessageId() {
        return topUsersMessageId;
    }

    public void setTopUsersMessageId(Integer topUsersMessageId) {
        this.topUsersMessageId = topUsersMessageId;
    }

    public Integer getDailyTaskMessageId() {
        return dailyTaskMessageId;
    }

    public void setDailyTaskMessageId(Integer dailyTaskMessageId) {
        this.dailyTaskMessageId = dailyTaskMessageId;
    }

    public Integer getDailyTaskAlertMessageId() {
        return dailyTaskAlertMessageId;
    }

    public void setDailyTaskAlertMessageId(Integer dailyTaskAlertMessageId) {
        this.dailyTaskAlertMessageId = dailyTaskAlertMessageId;
    }

    public Integer getDailyTaskProgressMessageId() {
        return dailyTaskProgressMessageId;
    }

    public void setDailyTaskProgressMessageId(Integer dailyTaskProgressMessageId) {
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
