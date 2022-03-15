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
    private Integer peopleCollectedIncomeOneToThreeTimes;

    @Column(nullable = false)
    private Integer peopleCollectedIncomeMoreTimes;

    @Column(nullable = false)
    private Integer peopleCompletedDailyTask;

    @Column(nullable = false)
    private Integer peopleSendsStickerOneOrMoreTimes;

    @Column(nullable = false)
    private Integer peopleDonated;

    @Column(nullable = false)
    private Integer peoplePutsStickerToAuction;

    @Column(nullable = false)
    private Integer groupCountWasAdded;

    @Column(nullable = false)
    private Integer groupCountWasActive;

    @Column(nullable = false)
    private Integer roulettePlayCount;

    @Column(nullable = false)
    private Integer groupPrizeCount;

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime date) {
        Date = date;
    }

    public Integer getPeopleCollectedIncomeOneToThreeTimes() {
        return peopleCollectedIncomeOneToThreeTimes;
    }

    public void setPeopleCollectedIncomeOneToThreeTimes(Integer peopleCollectedIncomeOneToThreeTimes) {
        this.peopleCollectedIncomeOneToThreeTimes = peopleCollectedIncomeOneToThreeTimes;
    }

    public Integer getPeopleCollectedIncomeMoreTimes() {
        return peopleCollectedIncomeMoreTimes;
    }

    public void setPeopleCollectedIncomeMoreTimes(Integer peopleCollectedIncomeMoreTimes) {
        this.peopleCollectedIncomeMoreTimes = peopleCollectedIncomeMoreTimes;
    }

    public Integer getPeopleCompletedDailyTask() {
        return peopleCompletedDailyTask;
    }

    public void setPeopleCompletedDailyTask(Integer peopleCompletedDailyTask) {
        this.peopleCompletedDailyTask = peopleCompletedDailyTask;
    }

    public Integer getPeopleSendsStickerOneOrMoreTimes() {
        return peopleSendsStickerOneOrMoreTimes;
    }

    public void setPeopleSendsStickerOneOrMoreTimes(Integer peopleSendsStickerOneOrMoreTimes) {
        this.peopleSendsStickerOneOrMoreTimes = peopleSendsStickerOneOrMoreTimes;
    }

    public Integer getPeopleDonated() {
        return peopleDonated;
    }

    public void setPeopleDonated(Integer peopleDonated) {
        this.peopleDonated = peopleDonated;
    }

    public Integer getPeoplePutsStickerToAuction() {
        return peoplePutsStickerToAuction;
    }

    public void setPeoplePutsStickerToAuction(Integer peoplePutsStickerToAuction) {
        this.peoplePutsStickerToAuction = peoplePutsStickerToAuction;
    }

    public Integer getGroupCountWasAdded() {
        return groupCountWasAdded;
    }

    public void setGroupCountWasAdded(Integer groupCountWasAdded) {
        this.groupCountWasAdded = groupCountWasAdded;
    }

    public Integer getGroupCountWasActive() {
        return groupCountWasActive;
    }

    public void setGroupCountWasActive(Integer groupCountWasActive) {
        this.groupCountWasActive = groupCountWasActive;
    }

    public Integer getRoulettePlayCount() {
        return roulettePlayCount;
    }

    public void setRoulettePlayCount(Integer roulettePlayCount) {
        this.roulettePlayCount = roulettePlayCount;
    }

    public Integer getGroupPrizeCount() {
        return groupPrizeCount;
    }

    public void setGroupPrizeCount(Integer groupPrizeCount) {
        this.groupPrizeCount = groupPrizeCount;
    }
}
