package com.javarush.test.level27.lesson15.big01.ad;


import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 309_newpower on 05.01.2016.
 */
public class AdvertisementManager
{
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds)
    {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException
    {
        List<Advertisement> list = storage.list();

        Comparator<Advertisement> compareByPrice = new Comparator<Advertisement>()
        {
            @Override
            public int compare(Advertisement o1, Advertisement o2)
            {
                if (o1.getAmountPerOneDisplaying() != o2.getAmountPerOneDisplaying())
                    return -Long.compare(o1.getAmountPerOneDisplaying(), o2.getAmountPerOneDisplaying());
                else
                    return Long.compare((o1.getAmountPerOneDisplaying() / o1.getDuration()), (o2.getAmountPerOneDisplaying() / o2.getDuration()));
            }
        };

        Collections.sort(list, compareByPrice);

        List<Advertisement> list1 = videoSort(list, timeSeconds);

//checking and sort adds for showing
        if (list1.size() == 0 || list1 == null) throw new NoVideoAvailableException();
        Collections.sort(list1, compareByPrice);

//register event
        long amount = 0;
        int totalDuration = 0;
        for (Advertisement advertisement : list1){
            amount += advertisement.getAmountPerOneDisplaying();
            totalDuration += advertisement.getDuration();
        }
        StatisticEventManager.getInstance().register(new VideoSelectedEventDataRow(list1, amount, totalDuration));

//showing the adds
        for (Advertisement advertisement : list1)
        {
            ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... "
                            + advertisement.getAmountPerOneDisplaying() + ", "
                            + (1000 * advertisement.getAmountPerOneDisplaying() / advertisement.getDuration())
            );
            advertisement.revalidate();
        }
    }

    private List<Advertisement> videoSort(List<Advertisement> adds, int timeSeconds)
    {
        List<Advertisement> resultList = new LinkedList<>();
        int copyTimeSeconds = timeSeconds;

        for (int i = 0; i < adds.size(); i++) {
            if (adds.get(i).getHits() > 0 && adds.get(i).getDuration() <= copyTimeSeconds) {
                resultList.add(adds.get(i));
                copyTimeSeconds -= adds.get(i).getDuration();
            }
        }

        for (int i = 0; i < adds.size(); i++)
        {
            List<Advertisement> tmpList = new LinkedList<>(adds);
            tmpList.remove(i);
            tmpList = videoSort(tmpList, timeSeconds);
            if (tmpList == null) return resultList;
            if (resultList == null) return tmpList;

            int moneyResult = 0;
            for (Advertisement advertisement : resultList)
                moneyResult += advertisement.getAmountPerOneDisplaying();
            int moneyTmp = 0;
            for (Advertisement advertisement : tmpList)
                moneyTmp += advertisement.getAmountPerOneDisplaying();
            if (moneyResult > moneyTmp) ;
            else if (moneyTmp > moneyResult) resultList = tmpList;

            else{
                int timeOfResult = 0;
                for (Advertisement advertisement : resultList)
                    timeOfResult += advertisement.getDuration();
                int timeOfTmp = 0;
                for (Advertisement advertisement : tmpList)
                    timeOfTmp += advertisement.getDuration();
                if (timeOfResult > timeOfTmp) ;
                else if (timeOfResult < timeOfTmp) resultList = tmpList;
                else {
                    if (resultList.size() > tmpList.size()) resultList = tmpList;
                }
            }
        }
        return resultList;
    }
}
