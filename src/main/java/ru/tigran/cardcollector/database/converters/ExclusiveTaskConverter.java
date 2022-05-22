package ru.tigran.cardcollector.database.converters;

import ru.tigran.cardcollector.enums.ExclusiveTask;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ExclusiveTaskConverter implements AttributeConverter<ExclusiveTask, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ExclusiveTask exclusiveTask) {
        return exclusiveTask.getValue();
    }

    @Override
    public ExclusiveTask convertToEntityAttribute(Integer integer) {
        for (ExclusiveTask exclusiveTask : ExclusiveTask.values()){
            if (exclusiveTask.getValue() == integer) return exclusiveTask;
        }
        return null;
    }
}