package com.javarush.test.level35.lesson08.bonus01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <K, T extends Convertable<K>> Map<K, T> convert(List<T> list) {
        Map<K, T> result = new HashMap();
        for (T a : list){
            result.put(a.getKey(), a);
        }
        return result;
    }
}
