package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
//import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by 309_newpower on 05.01.2016.
 */
public class Cook extends Observable implements Runnable
{
    private String name;
//    private boolean busy = false;
    private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();

    public void setQueue(LinkedBlockingQueue<Order> queue)
    {
        this.queue = queue;
    }

    public Cook(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public void startCookingOrder(Order order)
    {
//        busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + (order).getTotalCookingTime() + "min");
        StatisticEventManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), this.name, order.getTotalCookingTime() * 60, order.getDishes()));
        try
        {
            Thread.currentThread().sleep(order.getTotalCookingTime() * 10);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        setChanged();
        notifyObservers(order);
//        busy = false;
    }

//    public boolean isBusy()
//    {
//        return busy;
//    }

    @Override
    public void run()
    {
        while (true)
        {
            if (!queue.isEmpty())
            {
                try
                {
                    startCookingOrder(queue.take());
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }else
            {
                try
                {
                    Thread.currentThread().sleep(10);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
