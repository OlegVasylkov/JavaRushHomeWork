package com.javarush.test.level28.lesson06.home01;

/**
 * Created by 309_newpower on 18.01.2016.
 */
public class MyThread extends Thread
{
    private static int priority = 1;

    private void setPriority2(){
        int i = currentThread().getThreadGroup().getMaxPriority();
        if (priority < i)
            setPriority(priority);
        else setPriority(i);

        if (priority == 10){
            priority = 1;
        }else priority++;
    }

    public MyThread()
    {
        setPriority2();
    }

    public MyThread(Runnable target)
    {
        super(target);
        setPriority2();
    }

    public MyThread(ThreadGroup group, Runnable target)
    {
        super(group, target);
        setPriority2();
    }

    public MyThread(String name)
    {
        super(name);
        setPriority2();
    }

    public MyThread(ThreadGroup group, String name)
    {
        super(group, name);
        setPriority2();
    }

    public MyThread(Runnable target, String name)
    {
        super(target, name);
        setPriority2();
    }

    public MyThread(ThreadGroup group, Runnable target, String name)
    {
        super(group, target, name);
        setPriority2();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize)
    {
        super(group, target, name, stackSize);
        setPriority2();
    }



}
