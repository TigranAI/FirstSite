package ru.tigran.cardcollector.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user_level")
public class UserLevel {
    @Id
    @Column(name = "id") public Long UserId;
    @Column(name="level") public int Level;
    @Column(name="current_exp") public Long CurrentExp;
    @Column(name="total_exp") public Long TotalExp;

    public UserLevel(){}
    public UserLevel(long userId){
        UserId = userId;
        Level = 0;
        CurrentExp = 0L;
        TotalExp = 0L;
    }
}
