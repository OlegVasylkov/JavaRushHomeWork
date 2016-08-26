package com.javarush.test.level27.lesson15.big01.statistic.event;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 309_newpower on 10.01.2016.
 */
public class NoAvailableVideoEventDataRow implements EventDataRow
{
    private int totalDuration;
    private Date currentDate;

    public NoAvailableVideoEventDataRow(int totalDuration){
        this.totalDuration = totalDuration;
        currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        currentDate = cal.getTime();

    }

    @Override
    public EventType getType()
    {
        return EventType.NO_AVAILABLE_VIDEO;
    }

    @Override
    public Date getDate()
    {
        return currentDate;
    }

    @Override
    public int getTime()
    {
        return totalDuration;
    }
}
