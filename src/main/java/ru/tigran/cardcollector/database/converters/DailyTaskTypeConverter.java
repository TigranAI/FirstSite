package ru.tigran.cardcollector.database.converters;

import ru.tigran.cardcollector.enums.DailyTaskType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DailyTaskTypeConverter implements AttributeConverter<DailyTaskType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DailyTaskType dailyTaskType) {
        return dailyTaskType.getValue();
    }

    @Override
    public DailyTaskType convertToEntityAttribute(Integer integer) {
        for (DailyTaskType dailyTaskType : DailyTaskType.values()){
            if (dailyTaskType.getValue() == integer) return dailyTaskType;
        }
        return null;
    }
}
