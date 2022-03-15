package ru.tigran.cardcollector.database.converters;

import ru.tigran.cardcollector.enums.Prize;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PrizeConverter implements AttributeConverter<Prize, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Prize prize) {
        return prize.getValue();
    }

    @Override
    public Prize convertToEntityAttribute(Integer integer) {
        for (Prize prize : Prize.values()){
            if (prize.getValue() == integer) return prize;
        }
        return null;
    }
}
