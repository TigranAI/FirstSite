package ru.tigran.cardcollector.database.converters;

import ru.tigran.cardcollector.enums.ChatType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ChatTypeConverter implements AttributeConverter<ChatType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ChatType chatType) {
        return chatType.getValue();
    }

    @Override
    public ChatType convertToEntityAttribute(Integer integer) {
        for (ChatType chatType : ChatType.values()) {
            if (chatType.getValue() == integer) return chatType;
        }
        return null;
    }
}
