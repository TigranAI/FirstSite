package ru.tigran.cardcollector.functions;

import reactor.util.annotation.Nullable;
import ru.tigran.cardcollector.database.entity.Sticker;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListHelper {

    private static boolean NullOrEqual(@Nullable Object o1, @Nullable Object o2){
        return o1 == null || o2 == null || o1 == o2;
    }

    public static void FilterList(List<Sticker> list, String author, Integer tier, String emoji){
        if(!NullOrEqual(author, "")) list.removeIf(item -> !item.Author.equals(author));
        if(tier != null) list.removeIf(item -> !item.Tier.equals(tier));
        if(!NullOrEqual(emoji, "")) list.removeIf(item -> !item.Emoji.contains(emoji));
    }

    public static void SortList(List<Sticker> list, String sortParam){
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

    public static <E> List<E> GetRange(List<E> list, Integer offset, Integer length){
        return list.stream().skip(offset).limit(length).collect(Collectors.toList());
    }

    public static <E, R> List<R> Select(List<E> list, Function<E, R> expr){
        List<R> result = new LinkedList<>();
        list.forEach(item -> result.add(expr.apply(item)));
        return result;
    }

    public static <E, R> Hashtable<R, List<E>> GroupBy(List<E> list, Function<E, R> expr){
        Hashtable<R, List<E>> result = new Hashtable<>();
        list.forEach(item -> {
            R key = expr.apply(item);
            if (!result.containsKey(key)) result.put(key, new LinkedList<>());
            result.get(key).add(item);
        });
        return result;
    }

    public static <E, K, V> Hashtable<K, V> ToHashTable(List<E> list, Function<E, K> key, Function<E, V> value){
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
}
