package ru.tigran.cardcollector.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "cash")
public class Cash {
    @javax.persistence.Id
    @Column(name = "user_id") public Long Id;
    @Column(name = "coins") public int Coins;
    @Column(name = "gems") public int Gems;
    @Column(name = "max_capacity") public int MaxCapacity;
}