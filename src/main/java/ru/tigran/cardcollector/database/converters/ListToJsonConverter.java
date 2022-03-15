package ru.tigran.cardcollector.database.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class ListToJsonConverter implements AttributeConverter<List<Integer>, String> {
    @Override
    public String convertToDatabaseColumn(List<Integer> list) {
        if (list == null || list.isEmpty()) return "[]";
        return "[" + list.stream().map(Object::toString).collect(Collectors.joining(",")) + "]";
    }

    @Override
    public List<Integer> convertToEntityAttribute(String s) {
        if (s == null || s.equals("[]")) return new LinkedList<>();
        String[] items = s.substring(1, s.length() - 1).split(",");
        return Arrays.stream(items).map(Integer::parseInt).collect(Collectors.toList());
    }
}
