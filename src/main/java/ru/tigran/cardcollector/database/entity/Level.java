package ru.tigran.cardcollector.database.entity;

import ru.tigran.cardcollector.database.converters.LevelRewardConverter;
import ru.tigran.cardcollector.database.entity.notmapped.LevelReward;

import javax.persistence.*;

@Entity
@Table(name = "levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int levelValue;

    @Column(nullable = false)
    private long levelExpGoal;

    @Column(nullable = false, columnDefinition = "text")
    @Convert(converter = LevelRewardConverter.class)
    private LevelReward levelReward;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(int levelValue) {
        this.levelValue = levelValue;
    }

    public long getLevelExpGoal() {
        return levelExpGoal;
    }

    public void setLevelExpGoal(long levelExpGoal) {
        this.levelExpGoal = levelExpGoal;
    }

    public LevelReward getLevelReward() {
        return levelReward;
    }

    public void setLevelReward(LevelReward levelReward) {
        this.levelReward = levelReward;
    }
}