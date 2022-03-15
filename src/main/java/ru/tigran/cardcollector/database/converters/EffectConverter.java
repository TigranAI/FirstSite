package ru.tigran.cardcollector.database.converters;

import ru.tigran.cardcollector.enums.Effect;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EffectConverter implements AttributeConverter<Effect, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Effect effect) {
        return effect.getValue();
    }

    @Override
    public Effect convertToEntityAttribute(Integer integer) {
        for (Effect effect : Effect.values()){
            if (effect.getValue() == integer) return effect;
        }
        return null;
    }
}
