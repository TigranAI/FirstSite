package ru.tigran.cardcollector.database.converters;

import ru.tigran.cardcollector.enums.IncomeType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class IncomeTypeConverter implements AttributeConverter<IncomeType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(IncomeType incomeType) {
        return incomeType.getValue();
    }

    @Override
    public IncomeType convertToEntityAttribute(Integer integer) {
        for (IncomeType incomeType : IncomeType.values()){
            if (incomeType.getValue() == integer) return incomeType;
        }
        return null;
    }
}
