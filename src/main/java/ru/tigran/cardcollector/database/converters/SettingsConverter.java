package ru.tigran.cardcollector.database.converters;

import ru.tigran.cardcollector.enums.Settings;

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
        String[] params = s.substring(1, s.length() - 2).split(",");
        Map<Settings, Boolean> result = new HashMap<>();
        for (String param : params) {
            String[] pair = param.split(":");
            result.put(getEnumValue(Integer.parseInt(pair[0])), Boolean.parseBoolean(pair[1]));
        }
        return result;
    }

    private Settings getEnumValue(int value){
        for (Settings param: Settings.values()) {
            if (param.getValue() == value) return param;
        }
        return null;
    }
}
