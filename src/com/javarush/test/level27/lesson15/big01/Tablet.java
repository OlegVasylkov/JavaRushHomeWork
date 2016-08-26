package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.TestOrder;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import com.javarush.test.level27.lesson15.big01.statistic.event.NoAvailableVideoEventDataRow;

import java.io.IOException;
//import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.*;

/**
 * Created by 309_newpower on 30.12.2015.
 */
public class Tablet //extends Observable
{
    private static final Logger logger = Logger.getLogger(Tablet.class.getName());
    public final int number;
    public static AdvertisementManager advertisementManager;
    private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();

    public void setQueue(LinkedBlockingQueue<Order> queue)
    {
        this.queue = queue;
    }

    public Tablet(int number)
    {
        this.number = number;
    }

    public void createOrder() {
        try {
            Order order = new Order(this);
            ConsoleHelper.writeMessage(order.toString());
            //5. В Tablet часть логики, которая уведомляет Observer-а, замени на такую, которая добавляет заказ в очередь.
            queue.put(order);
//            setChanged();
//            notifyObservers(order);
            try {
                if (!order.isEmpty()) {
                    advertisementManager = new AdvertisementManager(order.getTotalCookingTime() * 60);
                    advertisementManager.processVideos();
                }
            }catch (NoVideoAvailableException e) {
                StatisticEventManager.getInstance().register(new NoAvailableVideoEventDataRow(order.getTotalCookingTime() * 60));
                logger.log(Level.INFO, "No video is available for the order " + order);
            }
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void createTestOrder(){
        try {
            TestOrder order = new TestOrder(this);
            ConsoleHelper.writeMessage(order.toString());
            //5. В Tablet часть логики, которая уведомляет Observer-а, замени на такую, которая добавляет заказ в очередь.
            queue.put(order);
//            setChanged();
//            notifyObservers(order);
            try {
                if (!order.isEmpty()) {
                    advertisementManager = new AdvertisementManager(order.getTotalCookingTime() * 60);
                    advertisementManager.processVideos();
                }
            }catch (NoVideoAvailableException e) {
                StatisticEventManager.getInstance().register(new NoAvailableVideoEventDataRow(order.getTotalCookingTime() * 60));
                logger.log(Level.INFO, "No video is available for the order " + order);
            }
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String toString()
    {
        return "Tablet{" + "number=" + number + "}";
    }
}
