package com.javarush.test.level08.lesson08.task04;

import java.util.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Удалить всех людей, родившихся летом
Создать словарь (Map<String, Date>) и занести в него десять записей по принципу: «фамилия» - «дата рождения».
Удалить из словаря всех людей, родившихся летом.
*/

public class Solution
{
    public static HashMap<String, Date> createMap()
    {
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Сталлоне0", new Date(1987, 0, 31));
        map.put("Сталлоне1", new Date(1987, 0, 31));
        map.put("Сталлоне2", new Date(1987, 0, 31));
        map.put("Сталлоне3", new Date(1987, 0, 31));
        map.put("Сталлоне4", new Date(1987, 0, 31));
        map.put("Сталлоне5", new Date(1987, 0, 31));
        map.put("Сталлоне6", new Date(1987, 5, 31));
        map.put("Сталлоне7", new Date(1987, 5, 31));
        map.put("Сталлоне8", new Date(1987, 5, 31));
        map.put("Сталлоне9", new Date(1987, 0, 31));

        return map;
         //Напишите тут ваш код

    }

    public static void removeAllSummerPeople(HashMap<String, Date> map)
    {
        Iterator<Map.Entry<String, Date>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            //записываем для краткости значение месяца в переменную
            int mounth = iterator.next().getValue().getMonth();
            //проверяется месяц, начиная с 0
            if ((mounth >= 5) && (mounth <= 7))
            {
                iterator.remove();
            }

            //Напишите тут ваш код
        }

    }
        //public static void main(String[] args){}
}
