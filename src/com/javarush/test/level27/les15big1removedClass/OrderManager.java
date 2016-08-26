package com.javarush.test.level27.les15big1removedClass;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by 309_newpower on 14.01.2016.
 */
public class OrderManager implements Observer
{
    LinkedBlockingQueue<Order> linkedBlockingQueue = new LinkedBlockingQueue<>();

    public OrderManager()
    {
        Thread thread1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {

//                while (true)
//                {
//                    Set<Cook> cookSet = StatisticEventManager.getInstance().getCookSet();
//                    for (Cook cook : cookSet)
//                    {
//                        while (!cook.isBusy())
//                        {
//                            if (!linkedBlockingQueue.isEmpty()){
//                                cook.startCookingOrder(linkedBlockingQueue.remove());
//                                break;
//                            }
//
//                            try
//                            {
//                                Thread.currentThread().sleep(10);
//                            }
//                            catch (InterruptedException e)
//                            {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
            }
        });
        thread1.setDaemon(true);
        thread1.start();
    }

    @Override
    public void update(Observable observable, Object arg)
    {
        linkedBlockingQueue.add((Order) arg);
    }
}
