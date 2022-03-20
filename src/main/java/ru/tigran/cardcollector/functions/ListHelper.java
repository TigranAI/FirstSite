package ru.tigran.cardcollector.functions;

import ru.tigran.cardcollector.Utilities;
import ru.tigran.cardcollector.database.entity.Sticker;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListHelper {

    public static <E> List<E> GetRange(List<E> list, Integer offset, Integer length) {
        return list.stream().skip(offset).limit(length).collect(Collectors.toList());
    }

    public static Sticker Random(List<Sticker> stickers) {
        return stickers.get(Utilities.rnd.nextInt(stickers.size()));
    }

    public static List<Sticker> Random(List<Sticker> stickers, int count) {
        List<Sticker> result = new LinkedList<>();
        for(int i = 0; i < count; ++i){
            result.add(Attach(stickers, Utilities.rnd.nextInt(stickers.size())));
        }
        return result;
    }

    public static <E> E Attach(List<E> source, int index){
        E result = source.get(index);
        source.remove(index);
        return result;
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
