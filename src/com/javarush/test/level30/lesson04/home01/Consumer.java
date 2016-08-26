package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by 309_newpower on 05.02.2016.
 */
public class Consumer implements Runnable
{
    private TransferQueue<ShareItem> queue;
    public Consumer(TransferQueue<ShareItem> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(500);
            while (!Thread.currentThread().isInterrupted()){
                ShareItem shareItem = queue.take();
                System.out.println("Processing " + shareItem.toString());
            }
        }
        catch (InterruptedException e)
        {
        }

    }
}
