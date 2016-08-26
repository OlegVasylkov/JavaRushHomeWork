package com.javarush.test.level08.lesson08.task03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* Одинаковые имя и фамилия
Создать словарь (Map<String, String>) занести в него десять записей по принципу «Фамилия» - «Имя».
Проверить сколько людей имеют совпадающие с заданным имя или фамилию.
*/

public class Solution
{
    public static HashMap<String, String> createMap()
    {
        HashMap<String, String> map = new HashMap<String, String>();
        for (int i = 0; i<10; i++){
            map.put("фамилия" + i, "имя" + i);
        }
        return map;
        //Напишите тут ваш код

    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name)
    {
        Iterator<Map.Entry<String,String>>iterator = map.entrySet().iterator();
        int i = 0;
        while (iterator.hasNext()){
            Map.Entry<String,String> pair = iterator.next();
            String name2 = pair.getValue();
            if (name.equals(name2)) i++;
        }
        return i;
        //Напишите тут ваш код
    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String familiya)
    {
        Iterator<Map.Entry<String,String>>iterator = map.entrySet().iterator();
        int i = 0;
        while (iterator.hasNext()){
            Map.Entry<String,String> pair = iterator.next();
            String name2 = pair.getKey();
            if (familiya.equals(name2)) i++;
        }
        return i;

        //Напишите тут ваш код

    }
}
