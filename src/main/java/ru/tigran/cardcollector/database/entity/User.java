package ru.tigran.cardcollector.database.entity;

import ru.tigran.cardcollector.database.converters.PrivilegeLevelConverter;
import ru.tigran.cardcollector.enums.PrivilegeLevel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long chatId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Boolean isBlocked;

    @Column(nullable = false)
    @Convert(converter = PrivilegeLevelConverter.class)
    private PrivilegeLevel privilegeLevel;

    @Column(nullable = false)
    private Boolean firstReward;

    @OneToOne(mappedBy = "user")
    private Cash cash;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private UserSettings settings;

    @OneToOne(mappedBy = "user")
    private UserLevel level;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private UserMessages messages;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserActivity> activityList;

    @OneToMany(mappedBy = "user")
    private List<UserSticker> stickers;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserPacks> packs;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserSendStickerToChat> sendStickerList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public PrivilegeLevel getPrivilegeLevel() {
        return privilegeLevel;
    }

    public void setPrivilegeLevel(PrivilegeLevel privilegeLevel) {
        this.privilegeLevel = privilegeLevel;
    }

    public Boolean isFirstReward() {
        return firstReward;
    }

    public void setFirstReward(Boolean firstReward) {
        this.firstReward = firstReward;
    }

    public Cash getCash() {
        return cash;
    }

    public void setCash(Cash cash) {
        this.cash = cash;
    }

    public UserSettings getSettings() {
        return settings;
    }

    public void setSettings(UserSettings settings) {
        this.settings = settings;
    }

    public UserLevel getLevel() {
        return level;
    }

    public void setLevel(UserLevel level) {
        this.level = level;
    }

    public UserMessages getMessages() {
        return messages;
    }

    public void setMessages(UserMessages messages) {
        this.messages = messages;
    }

    public List<UserActivity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<UserActivity> activityList) {
        this.activityList = activityList;
    }

    public List<UserSticker> getStickers() {
        return stickers;
    }

    public void setStickers(List<UserSticker> stickers) {
        this.stickers = stickers;
    }

    public List<UserPacks> getPacks() {
        return packs;
    }

    public void setPacks(List<UserPacks> packs) {
        this.packs = packs;
    }

    public List<UserSendStickerToChat> getSendStickerList() {
        return sendStickerList;
    }

    public void setSendStickerList(List<UserSendStickerToChat> sendStickerList) {
        this.sendStickerList = sendStickerList;
    }
}
