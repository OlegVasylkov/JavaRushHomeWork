package com.javarush.test.level27.lesson15.big01;


//import com.javarush.tests.level27.les15big1removedClass.OrderManager;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by 309_newpower on 30.12.2015.
 */
public class Restaurant
{
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> ORDER_LINKED_BLOCKING_QUEUE = new LinkedBlockingQueue<>();


    public static void main(String[] args)
    {
        Locale.setDefault(Locale.ENGLISH);

        Cook cook = new Cook("Amigo");
        Cook cook1 = new Cook("Bender");
        Cook cook2 = new Cook("Bender2");
        cook.setQueue(ORDER_LINKED_BLOCKING_QUEUE);
        cook1.setQueue(ORDER_LINKED_BLOCKING_QUEUE);
        cook2.setQueue(ORDER_LINKED_BLOCKING_QUEUE);

        Waitor waitor = new Waitor();
        cook.addObserver(waitor);
        cook1.addObserver(waitor);
        cook2.addObserver(waitor);

        Thread thread = new Thread(cook);
        Thread thread1 =  new Thread(cook1);
        Thread thread2 = new Thread(cook2);
//        thread.setDaemon(true);
//        thread1.setDaemon(true);
//        thread2.setDaemon(true);
        thread.start();
        thread1.start();
        thread2.start();

        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5; i++)
        {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(ORDER_LINKED_BLOCKING_QUEUE);
            tablets.add(tablet);
        }
        Thread orderThread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        orderThread.start();

        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        orderThread.interrupt();
        thread.interrupt();
        thread1.interrupt();
        thread2.interrupt();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();

    }
}
