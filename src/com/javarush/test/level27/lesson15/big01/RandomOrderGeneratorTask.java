package com.javarush.test.level27.lesson15.big01;

import java.util.List;

/**
 * Created by 309_newpower on 14.01.2016.
 */
public class RandomOrderGeneratorTask implements Runnable
{
    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval)
    {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run()
    {
        while (!Thread.currentThread().isInterrupted()){
            try
            {
                Tablet t = tablets.get((int)(Math.random()*tablets.size()));
                t.createTestOrder();
                Thread.sleep(interval);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
}
