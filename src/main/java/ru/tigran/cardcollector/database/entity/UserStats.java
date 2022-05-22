package ru.tigran.cardcollector.database.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users_stats")
public class UserStats {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Long earnedExp = 0L;
    @Column(nullable = false)
    private LocalDateTime lastExpAccrual = LocalDateTime.now();

    @Column(nullable = false)
    private Integer earned4TierStickers = 0;
    @Column(nullable = false)
    private LocalDateTime last4TierStickerAccrual = LocalDateTime.now();;

    @Column(nullable = false)
    private Integer rouletteGames = 0;
    @Column(nullable = false)
    private LocalDateTime lastRouletteGame = LocalDateTime.now();;

    @Column(nullable = false)
    private Integer ladderGames = 0;
    @Column(nullable = false)
    private LocalDateTime lastLadderGame = LocalDateTime.now();;

    @Column(nullable = false)
    private Integer puzzleGames = 0;
    @Column(nullable = false)
    private LocalDateTime lastPuzzleGame = LocalDateTime.now();;

    @Column(nullable = false)
    private Integer giftsReceived = 0;
    @Column(nullable = false)
    private LocalDateTime lastReceivedGift = LocalDateTime.now();;

    @Column(nullable = false)
    private Integer friendsInvited = 0;
    @Column(nullable = false)
    private LocalDateTime lastInvitedFriend = LocalDateTime.now();;

    public UserStats() {
    }

    public UserStats(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getEarnedExp() {
        return earnedExp;
    }

    public void setEarnedExp(Long earnedExp) {
        this.earnedExp = earnedExp;
    }

    public LocalDateTime getLastExpAccrual() {
        return lastExpAccrual;
    }

    public void setLastExpAccrual(LocalDateTime lastExpAccrual) {
        this.lastExpAccrual = lastExpAccrual;
    }

    public Integer getEarned4TierStickers() {
        return earned4TierStickers;
    }

    public void setEarned4TierStickers(Integer earned4TierStickers) {
        this.earned4TierStickers = earned4TierStickers;
    }

    public LocalDateTime getLast4TierStickerAccrual() {
        return last4TierStickerAccrual;
    }

    public void setLast4TierStickerAccrual(LocalDateTime last4TierStickerAccrual) {
        this.last4TierStickerAccrual = last4TierStickerAccrual;
    }

    public Integer getRouletteGames() {
        return rouletteGames;
    }

    public void setRouletteGames(Integer rouletteGames) {
        this.rouletteGames = rouletteGames;
    }

    public LocalDateTime getLastRouletteGame() {
        return lastRouletteGame;
    }

    public void setLastRouletteGame(LocalDateTime lastRouletteGame) {
        this.lastRouletteGame = lastRouletteGame;
    }

    public Integer getLadderGames() {
        return ladderGames;
    }

    public void setLadderGames(Integer ladderGames) {
        this.ladderGames = ladderGames;
    }

    public LocalDateTime getLastLadderGame() {
        return lastLadderGame;
    }

    public void setLastLadderGame(LocalDateTime lastLadderGame) {
        this.lastLadderGame = lastLadderGame;
    }

    public Integer getPuzzleGames() {
        return puzzleGames;
    }

    public void setPuzzleGames(Integer puzzleGames) {
        this.puzzleGames = puzzleGames;
    }

    public LocalDateTime getLastPuzzleGame() {
        return lastPuzzleGame;
    }

    public void setLastPuzzleGame(LocalDateTime lastPuzzleGame) {
        this.lastPuzzleGame = lastPuzzleGame;
    }

    public Integer getGiftsReceived() {
        return giftsReceived;
    }

    public void setGiftsReceived(Integer giftsReceived) {
        this.giftsReceived = giftsReceived;
    }

    public LocalDateTime getLastReceivedGift() {
        return lastReceivedGift;
    }

    public void setLastReceivedGift(LocalDateTime lastReceivedGift) {
        this.lastReceivedGift = lastReceivedGift;
    }

    public Integer getFriendsInvited() {
        return friendsInvited;
    }

    public void setFriendsInvited(Integer friendsInvited) {
        this.friendsInvited = friendsInvited;
    }

    public LocalDateTime getLastInvitedFriend() {
        return lastInvitedFriend;
    }

    public void setLastInvitedFriend(LocalDateTime lastInvitedFriend) {
        this.lastInvitedFriend = lastInvitedFriend;
    }
}
