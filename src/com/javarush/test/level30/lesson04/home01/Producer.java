package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by 309_newpower on 05.02.2016.
 */
public class Producer implements Runnable
{
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        try
        {
            for (int N = 1; N < 10; N++)
            {
                ShareItem shareItem = new ShareItem("ShareItem-" + N, N);
                System.out.format("Элемент \'" + shareItem.getDescription() + "\' добавлен" + "%n");
                queue.offer(shareItem);
                Thread.sleep(100);
                if (queue.hasWaitingConsumer()) System.out.println("Consumer в ожидании!");
            }
            while (!Thread.currentThread().isInterrupted());
        }
        catch (InterruptedException e)
        {
        }
    }
}

