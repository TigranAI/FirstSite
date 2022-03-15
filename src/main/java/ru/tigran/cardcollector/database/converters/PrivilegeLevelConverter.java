package ru.tigran.cardcollector.database.converters;

import ru.tigran.cardcollector.enums.PrivilegeLevel;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PrivilegeLevelConverter implements AttributeConverter<PrivilegeLevel, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PrivilegeLevel privilegeLevel) {
        return privilegeLevel.getValue();
    }

    @Override
    public PrivilegeLevel convertToEntityAttribute(Integer integer) {
        for(PrivilegeLevel privilegeLevel : PrivilegeLevel.values()){
            if (privilegeLevel.getValue() == integer) return privilegeLevel;
        }
        return null;
    }
}
