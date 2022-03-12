package ru.tigran.cardcollector.functions;

import ru.tigran.cardcollector.Utilities;
import ru.tigran.cardcollector.database.entity.Sticker;
import ru.tigran.cardcollector.database.entity.UserSticker;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListHelper {

    public static <E, R> void FilterListBy(List<E> list, Function<E, R> expr, R value) {
        list.removeIf(item -> value != null && !value.toString().equals("") && !expr.apply(item).equals(value));
    }

    public static void SortList(List<Sticker> list, String sortParam) {
        switch (sortParam) {
            case "author":
                list.sort(Comparator.comparing(o -> o.Author, String::compareToIgnoreCase));
                break;
            case "tier":
                list.sort(Comparator.comparingInt(o -> o.Tier));
                break;
            case "tier_desc":
                list.sort((o1, o2) -> o2.Tier - o1.Tier);
                break;
            case "title":
                list.sort(Comparator.comparing(o -> o.Title, String::compareToIgnoreCase));
                break;
        }
    }

    public static <E> List<E> GetRange(List<E> list, Integer offset, Integer length) {
        return list.stream().skip(offset).limit(length).collect(Collectors.toList());
    }

    public static <E, R> List<R> Select(List<E> list, Function<E, R> expr) {
        List<R> result = new LinkedList<>();
        list.forEach(item -> result.add(expr.apply(item)));
        return result;
    }

    public static <E, R> Hashtable<R, List<E>> GroupBy(List<E> list, Function<E, R> expr) {
        Hashtable<R, List<E>> result = new Hashtable<>();
        list.forEach(item -> {
            R key = expr.apply(item);
            if (!result.containsKey(key)) result.put(key, new LinkedList<>());
            result.get(key).add(item);
        });
        return result;
    }

    public static <E, K, V> Hashtable<K, V> ToHashTable(List<E> list, Function<E, K> key, Function<E, V> value) {
        Hashtable<K, V> result = new Hashtable<>();
        list.forEach(item ->
                result.put(key.apply(item), value.apply(item))
        );
        return result;
    }

    public static <E, R> List<R> ToList(List<E> source, Function<E, R> expr) {
        List<R> result = new LinkedList<>();
        source.forEach(item -> result.add(expr.apply(item)));
        return result;
    }

    public static Sticker Random(ArrayList<Sticker> stickers) {
        return stickers.get(Utilities.rnd.nextInt(stickers.size()));
    }

    public static <E> int FindIndexOf(List<E> source, Function<E, Boolean> expr) {
        int i = 0;
        for (E item : source) {
            if (expr.apply(item)) return i;
            ++i;
        }
        return -1;
    }
}
