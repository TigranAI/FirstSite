package ru.tigran.cardcollector.database.entity;

import ru.tigran.cardcollector.database.converters.PrizeConverter;
import ru.tigran.cardcollector.enums.Prize;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "channel_giveaways")
public class ChannelGiveaway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Convert(converter = PrizeConverter.class)
    private Prize prize;

    @Column(nullable = false)
    private int prizeCount;

    @Column(columnDefinition = "longtext")
    private String message;

    @Column(columnDefinition = "longtext")
    private String buttonText;

    @Column(columnDefinition = "longtext")
    private String imageFileId;

    @Column(nullable = false)
    private int messageId;

    private LocalDateTime sendAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "selected_sticker_id")
    private Sticker selectedSticker;

    private int selectedStickerTier;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", nullable = false)
    private TelegramChat channel;

    @ManyToMany
    @JoinTable(name = "channel_giveaway_user",
            joinColumns = @JoinColumn(name = "used_giveaways_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "awarded_users_id", nullable = false))
    private List<User> awardedUsers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }

    public int getPrizeCount() {
        return prizeCount;
    }

    public void setPrizeCount(int prizeCount) {
        this.prizeCount = prizeCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getImageFileId() {
        return imageFileId;
    }

    public void setImageFileId(String imageFileId) {
        this.imageFileId = imageFileId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public LocalDateTime getSendAt() {
        return sendAt;
    }

    public void setSendAt(LocalDateTime sendAt) {
        this.sendAt = sendAt;
    }

    public Sticker getSelectedSticker() {
        return selectedSticker;
    }

    public void setSelectedSticker(Sticker selectedSticker) {
        this.selectedSticker = selectedSticker;
    }

    public int getSelectedStickerTier() {
        return selectedStickerTier;
    }

    public void setSelectedStickerTier(int selectedStickerTier) {
        this.selectedStickerTier = selectedStickerTier;
    }

    public TelegramChat getChannel() {
        return channel;
    }

    public void setChannel(TelegramChat channel) {
        this.channel = channel;
    }

    public List<User> getAwardedUsers() {
        return awardedUsers;
    }

    public void setAwardedUsers(List<User> awardedUsers) {
        this.awardedUsers = awardedUsers;
    }
}
