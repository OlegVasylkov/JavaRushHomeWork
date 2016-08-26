package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.HashBiMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 309_newpower on 27.06.2016.
 */
public class SpeedTest
{
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Date start = new Date();
        for (String s : strings){
            ids.add(shortener.getId(s));
        }
        Date end = new Date();
        return end.getTime() - start.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        Date start = new Date();
        for (Long l : ids){
            strings.add(shortener.getString(l));
        }
        Date end = new Date();
        return end.getTime() - start.getTime();
    }

    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++){
            origStrings.add(Helper.generateRandomString());
        }
        Set<Long> setIds1 = new HashSet<>();
        Set<Long> setIds2 = new HashSet<>();
        Long time1 = getTimeForGettingIds(shortener1, origStrings, setIds1);
        Long time2 = getTimeForGettingIds(shortener2, origStrings, setIds2);
        Assert.assertTrue(time1 > time2);
        Set<String> stringSet1 = new HashSet<>();
        Set<String> stringSet2 = new HashSet<>();
        Long t1 = getTimeForGettingStrings(shortener1, setIds1, stringSet1);
        Long t2 = getTimeForGettingStrings(shortener2, setIds2, stringSet2);
        Assert.assertEquals(t1, t2, 5);
    }
}
