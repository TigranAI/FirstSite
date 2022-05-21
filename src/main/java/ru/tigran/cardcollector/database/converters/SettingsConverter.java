package ru.tigran.cardcollector.database.converters;

import ru.tigran.cardcollector.enums.Settings;
import ru.tigran.cardcollector.others.JSON;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class SettingsConverter implements AttributeConverter<Map<Settings, Boolean>, String> {
    @Override
    public String convertToDatabaseColumn(Map<Settings, Boolean> settingsBooleanDictionary) {
        return "{" +
                settingsBooleanDictionary
                        .entrySet()
                        .stream()
                        .map(this::entryToString)
                        .collect(Collectors.joining(","))
                + "}";
    }

    private String entryToString(Map.Entry<Settings, Boolean> entry) {
        return "\"" + entry.getKey().getValue() + "\"" + ":" + entry.getValue().toString();
    }

    @Override
    public Map<Settings, Boolean> convertToEntityAttribute(String s) {
        Map<String, Boolean> entryMap = (Map<String, Boolean>) JSON.DeserializeObject(s, Map.class);
        if (entryMap == null) return new HashMap<>();
        Map<Settings, Boolean> result = new HashMap<>();
        for (var pair : entryMap.entrySet()){
            Integer key = Integer.parseInt(pair.getKey());
            Boolean value = pair.getValue();
            result.put(getEnumValue(key), value);
        }
        return result;
    }

    private Settings getEnumValue(Integer value) {
        for (Settings param : Settings.values()) {
            if (param.getValue() == value) return param;
        }
        return null;
    }
}
