package ru.tigran.cardcollector.functions;

import java.util.*;

public class DictionaryHelper {
    @FunctionalInterface
    public interface Function3<T1, T2, R> {
        public R apply(T1 t1, T2 t2);
    }

    public static <K1, V1, K2, V2> Hashtable<K2, V2> ToHashTable(Hashtable<K1, V1> source,
                                                                 Function3<K1, V1, K2> key,
                                                                 Function3<K1, V1, V2> value) {
        Hashtable<K2, V2> result = new Hashtable<>();
        source.forEach((k, v) -> {
            result.put(key.apply(k, v), value.apply(k, v));
        });
        return result;
    }

    public static <K1, V1, K2, V2> List<Map.Entry<K2, V2>> ToList(Hashtable<K1, V1> source,
                                                                  Function3<K1, V1, K2> key,
                                                                  Function3<K1, V1, V2> value) {
        List<Map.Entry<K2, V2>> result = new LinkedList<>();
        source.forEach((k, v) ->
                result.add(new AbstractMap.SimpleEntry<>(key.apply(k, v), value.apply(k, v)))
        );
        return result;
    }
}
