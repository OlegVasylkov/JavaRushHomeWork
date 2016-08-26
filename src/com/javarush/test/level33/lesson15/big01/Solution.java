package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 309_newpower on 16.06.2016.
 */
public class Solution
{
    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> idsSet = new HashSet<>();
        for (String s : strings)
            idsSet.add(shortener.getId(s));
        return idsSet;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> stringSet = new HashSet<>();
        for (Long l : keys)
            stringSet.add(shortener.getString(l));
        return stringSet;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> stringSet = new HashSet<>();
        for (long l = 0; l < elementsNumber; l++)
        {
            stringSet.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        Date start = new Date();
        Set<Long> idsSet = getIds(shortener, stringSet);
        Date end = new Date();
        Helper.printMessage(String.valueOf((end.getTime() - start.getTime())));
        start = new Date();
        Set<String> stringSet1 = getStrings(shortener, idsSet);
        end = new Date();
        Helper.printMessage(String.valueOf((end.getTime() - start.getTime())));
        if(stringSet.equals(stringSet1))
            Helper.printMessage("Тест пройден.");
        else Helper.printMessage("Тест не пройден.");
    }

    public static void main(String[] args)
    {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new FileStorageStrategy(), 100);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        testStrategy(new HashBiMapStorageStrategy(), 10000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);

    }
}
