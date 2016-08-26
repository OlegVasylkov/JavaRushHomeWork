package com.javarush.test.level27.lesson15.big01.statistic;

//import com.javarush.tests.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

/**
 * Created by 309_newpower on 10.01.2016.
 */
public class StatisticEventManager
{
    private static StatisticEventManager ourInstance = new StatisticEventManager();
    private StatisticStorage statisticStorage = new StatisticStorage();
    //private static Set<Cook> cookSet = new HashSet<>();

    public static StatisticEventManager getInstance()
    {
        return ourInstance;
    }

    private StatisticEventManager()
    {
    }

    public void register(EventDataRow data)
    {
        statisticStorage.put(data);
    }

//    public void register(Cook cook)
//    {
//        cookSet.add(cook);
//    }
//
//    public Set<Cook> getCookSet()
//    {
//        return cookSet;
//    }

    private static class StatisticStorage
    {
        private Map<EventType, List<EventDataRow>> map = new HashMap<>();

        private StatisticStorage()
        {
            for (EventType eventType : EventType.values())
                map.put(eventType, new ArrayList<EventDataRow>());
        }

        private void put(EventDataRow data)
        {
            map.get(data.getType()).add(data);
        }

        public Map<EventType, List<EventDataRow>> getMap()
        {
            return map;
        }
    }

    public Map<Date, Long> advertisementProfit()
    {
        List<EventDataRow> list = statisticStorage.getMap().get(EventType.SELECTED_VIDEOS);

        Map<Date, Long> result = new HashMap<>();
        for (EventDataRow eventDataRow : list)
        {
            VideoSelectedEventDataRow videoSelectedEventDataRow = (VideoSelectedEventDataRow) eventDataRow;
            Date date = eventDataRow.getDate();
            if (result.containsKey(date))
                result.put(date, (result.get(date) + videoSelectedEventDataRow.getAmount()));
            else result.put(date, videoSelectedEventDataRow.getAmount());
        }
        return result;
    }

    public Map<Date, TreeMap<String, Integer>> cookWorkloading()
    {
        List<EventDataRow> list = statisticStorage.getMap().get(EventType.COOKED_ORDER);

        Map<Date, TreeMap<String, Integer>> result = new HashMap<>();
        for (EventDataRow eventDataRow : list)
        {
            CookedOrderEventDataRow cookedOrderEventDataRow = (CookedOrderEventDataRow) eventDataRow;
            Date date = eventDataRow.getDate();
            if (result.containsKey(date))
            {
                TreeMap<String, Integer> cooksTime = result.get(date);
                if (cooksTime.containsKey(cookedOrderEventDataRow.getCookName()))
                    cooksTime.put(cookedOrderEventDataRow.getCookName(), cooksTime.get(cookedOrderEventDataRow.getCookName()) + cookedOrderEventDataRow.getTime());
                else
                    cooksTime.put(cookedOrderEventDataRow.getCookName(), cookedOrderEventDataRow.getTime());
                result.put(date, cooksTime);
            }
            else{
                TreeMap<String, Integer> cooksTime = new TreeMap();
                cooksTime.put(cookedOrderEventDataRow.getCookName(), cookedOrderEventDataRow.getTime());
                result.put(date, cooksTime);
            }
        }
        return result;
    }
}
