package ru.tigran.cardcollector.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "count_logs")
public class CountLogs {
    @Id
    private LocalDateTime Date = LocalDate.now().atStartOfDay();

    @Column(nullable = false)
    private int peopleCollectedIncomeOneToThreeTimes;

    @Column(nullable = false)
    private int peopleCollectedIncomeMoreTimes;

    @Column(nullable = false)
    private int peopleCompletedDailyTask;

    @Column(nullable = false)
    private int peopleSendsStickerOneOrMoreTimes;

    @Column(nullable = false)
    private int peopleDonated;

    @Column(nullable = false)
    private int peoplePutsStickerToAuction;

    @Column(nullable = false)
    private int groupCountWasAdded;

    @Column(nullable = false)
    private int groupCountWasActive;

    @Column(nullable = false)
    private int roulettePlayCount;

    @Column(nullable = false)
    private int groupPrizeCount;

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime date) {
        Date = date;
    }

    public int getPeopleCollectedIncomeOneToThreeTimes() {
        return peopleCollectedIncomeOneToThreeTimes;
    }

    public void setPeopleCollectedIncomeOneToThreeTimes(int peopleCollectedIncomeOneToThreeTimes) {
        this.peopleCollectedIncomeOneToThreeTimes = peopleCollectedIncomeOneToThreeTimes;
    }

    public int getPeopleCollectedIncomeMoreTimes() {
        return peopleCollectedIncomeMoreTimes;
    }

    public void setPeopleCollectedIncomeMoreTimes(int peopleCollectedIncomeMoreTimes) {
        this.peopleCollectedIncomeMoreTimes = peopleCollectedIncomeMoreTimes;
    }

    public int getPeopleCompletedDailyTask() {
        return peopleCompletedDailyTask;
    }

    public void setPeopleCompletedDailyTask(int peopleCompletedDailyTask) {
        this.peopleCompletedDailyTask = peopleCompletedDailyTask;
    }

    public int getPeopleSendsStickerOneOrMoreTimes() {
        return peopleSendsStickerOneOrMoreTimes;
    }

    public void setPeopleSendsStickerOneOrMoreTimes(int peopleSendsStickerOneOrMoreTimes) {
        this.peopleSendsStickerOneOrMoreTimes = peopleSendsStickerOneOrMoreTimes;
    }

    public int getPeopleDonated() {
        return peopleDonated;
    }

    public void setPeopleDonated(int peopleDonated) {
        this.peopleDonated = peopleDonated;
    }

    public int getPeoplePutsStickerToAuction() {
        return peoplePutsStickerToAuction;
    }

    public void setPeoplePutsStickerToAuction(int peoplePutsStickerToAuction) {
        this.peoplePutsStickerToAuction = peoplePutsStickerToAuction;
    }

    public int getGroupCountWasAdded() {
        return groupCountWasAdded;
    }

    public void setGroupCountWasAdded(int groupCountWasAdded) {
        this.groupCountWasAdded = groupCountWasAdded;
    }

    public int getGroupCountWasActive() {
        return groupCountWasActive;
    }

    public void setGroupCountWasActive(int groupCountWasActive) {
        this.groupCountWasActive = groupCountWasActive;
    }

    public int getRoulettePlayCount() {
        return roulettePlayCount;
    }

    public void setRoulettePlayCount(int roulettePlayCount) {
        this.roulettePlayCount = roulettePlayCount;
    }

    public int getGroupPrizeCount() {
        return groupPrizeCount;
    }

    public void setGroupPrizeCount(int groupPrizeCount) {
        this.groupPrizeCount = groupPrizeCount;
    }
}
