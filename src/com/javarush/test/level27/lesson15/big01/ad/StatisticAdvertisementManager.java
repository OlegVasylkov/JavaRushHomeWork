package com.javarush.test.level27.lesson15.big01.ad;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by 309_newpower on 13.01.2016.
 */
public class StatisticAdvertisementManager
{
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();

    public static StatisticAdvertisementManager getInstance()
    {
        return ourInstance;
    }

    private StatisticAdvertisementManager()
    {
    }

    public Set<Advertisement> getActiveVideoSet(){
        Set<Advertisement> activeVideoSet = new TreeSet<>(new Comparator()
        {
            @Override
            public int compare(Object o1, Object o2)
            {
                return ((Advertisement)o1).getName().compareToIgnoreCase(((Advertisement) o2).getName());
            }
        });
        for (Advertisement advertisement : advertisementStorage.list()){
            if (advertisement.getHits() > 0)
                activeVideoSet.add(advertisement);
        }
        return activeVideoSet;
    }

    public Set<Advertisement> getArchivedVideoSet(){
        Set<Advertisement> archivedVideoSet = new TreeSet<>(new Comparator()
        {
            @Override
            public int compare(Object o1, Object o2)
            {
                return ((Advertisement)o1).getName().compareToIgnoreCase(((Advertisement)o2).getName());
            }
        });
        for (Advertisement advertisement : advertisementStorage.list()){
            if (advertisement.getHits() == 0) archivedVideoSet.add(advertisement);
        }
        return archivedVideoSet;
    }
}
