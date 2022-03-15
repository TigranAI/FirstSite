package ru.tigran.cardcollector.database.entity.notmapped;

import org.springframework.lang.Nullable;

import java.io.Serializable;

public class LevelReward implements Serializable {
    @Nullable
    private Integer cashCapacity = null;
    @Nullable
    private Integer randomPacks = null;
    @Nullable
    private Integer randomStickerTier = null;
}
