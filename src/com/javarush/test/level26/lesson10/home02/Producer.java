package com.javarush.test.level26.lesson10.home02;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 309_newpower on 17.12.2015.
 */
public class Producer implements Runnable
{
    protected ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map)
    {
        this.map = map;
    }

    int count = 0;

    public void run()
    {
        try
        {
            while (true)
            {
                count++;
                map.put(String.valueOf(count),"Some text for "+count);
                Thread.sleep(500);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
        }
    }
}
