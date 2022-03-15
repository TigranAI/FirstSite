package ru.tigran.cardcollector.database.converters;

import ru.tigran.cardcollector.database.entity.notmapped.LevelReward;
import ru.tigran.cardcollector.others.JSON;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LevelRewardConverter implements AttributeConverter<LevelReward, String> {
    @Override
    public String convertToDatabaseColumn(LevelReward levelReward) {
        return JSON.SerializeObject(levelReward);
    }

    @Override
    public LevelReward convertToEntityAttribute(String s) {
        return JSON.DeserializeObject(s, LevelReward.class);
    }
}
