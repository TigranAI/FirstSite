package ru.tigran.cardcollector.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "cash")
public class Cash {
    @Id
    @Column(name = "user_id") public Long Id;
    @Column(name = "coins") public int Coins;
    @Column(name = "gems") public int Gems;
    @Column(name = "max_capacity") public int MaxCapacity;

    public Cash() {}
    public Cash(Long userId){
        Id = userId;
        Coins = 0;
        Gems = 0;
        MaxCapacity = 200;
    }
}