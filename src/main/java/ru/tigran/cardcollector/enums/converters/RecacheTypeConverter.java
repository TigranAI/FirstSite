package ru.tigran.cardcollector.enums.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.tigran.cardcollector.enums.RecacheType;

@Component
public class RecacheTypeConverter implements Converter<String, RecacheType> {

    @Override
    public RecacheType convert(String source) {
        int value = Integer.parseInt(source);
        for (RecacheType result : RecacheType.values()){
            if (result.getValue() == value) return result;
        }
        return null;
    }
}
