package com.javarush.test.level27.lesson15.big01;



import com.javarush.test.level27.lesson15.big01.ad.Advertisement;
import com.javarush.test.level27.lesson15.big01.ad.StatisticAdvertisementManager;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 309_newpower on 10.01.2016.
 */
public class DirectorTablet
{
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public  void printAdvertisementProfit(){

        Map<Date, Long> sortedMap = new TreeMap(Collections.reverseOrder());
        sortedMap.putAll(StatisticEventManager.getInstance().advertisementProfit());

        double total = 0.0;
        for (Map.Entry<Date, Long> data : sortedMap.entrySet()){
            ConsoleHelper.writeMessage(String.format("%s - %.2f", dateFormat.format(data.getKey()), data.getValue() / 100.0));
            total += data.getValue()/100.0;
        }
        ConsoleHelper.writeMessage(String.format("Total - %.2f", total));
    }

    public  void printCookWorkloading(){

        Map<Date, TreeMap<String, Integer>> sortedMap = new TreeMap(Collections.reverseOrder());
        sortedMap.putAll(StatisticEventManager.getInstance().cookWorkloading());

        for (Map.Entry<Date, TreeMap<String, Integer>> data : sortedMap.entrySet()){

            ConsoleHelper.writeMessage(dateFormat.format(data.getKey()));
            for (Map.Entry<String, Integer> data2 : data.getValue().entrySet()){
                if (data2.getValue()>0)
                    ConsoleHelper.writeMessage(String.format("%s - %d min", data2.getKey(),  (data2.getValue()+59)/60));
            }
            ConsoleHelper.writeMessage("");
        }
    }

    public  void printActiveVideoSet(){
        Set<Advertisement> set = StatisticAdvertisementManager.getInstance().getActiveVideoSet();
        for (Advertisement advertisement : set){
            ConsoleHelper.writeMessage(String.format("%s - %d", advertisement.getName(), advertisement.getHits()));
        }
    }
    public  void printArchivedVideoSet(){
        Set<Advertisement> set = StatisticAdvertisementManager.getInstance().getArchivedVideoSet();
        for (Advertisement advertisement : set){
            ConsoleHelper.writeMessage(advertisement.getName());
        }
    }
}
