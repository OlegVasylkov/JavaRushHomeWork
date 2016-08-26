package com.javarush.test.level25.lesson05.home01;

/**
 * Created by 309_newpower on 08.12.2015.
 */
public class LoggingStateThread extends Thread
{
    Thread target;
    public LoggingStateThread(Thread thread){
        this.target = thread;
        this.setDaemon(true);
    }

    public void run()
    {
        State state = target.getState();
        System.out.println(target.getState());

        while(state != State.TERMINATED)
        {
            if (state != target.getState())
            {
                state = target.getState();
                System.out.println(target.getState());

            }
        }
        interrupt();
    }
}