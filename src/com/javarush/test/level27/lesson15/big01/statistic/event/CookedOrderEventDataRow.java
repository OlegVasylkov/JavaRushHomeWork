package com.javarush.test.level27.lesson15.big01.statistic.event;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 309_newpower on 10.01.2016.
 */
public class CookedOrderEventDataRow implements EventDataRow
{
    private String tabletName;
    private String cookName;
    private int cookingTimeSeconds;
    private List<Dish> cookingDishs;
    private Date currentDate;

    public CookedOrderEventDataRow(String tabletName, String cookName, int cookingTimeSeconds, List<Dish> cookingDishs){
        this.tabletName = tabletName;
        this.cookName = cookName;
        this.cookingTimeSeconds = cookingTimeSeconds;
        this.cookingDishs = cookingDishs;
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
        return EventType.COOKED_ORDER;
    }

    @Override
    public Date getDate()
    {
        return currentDate;
    }

    @Override
    public int getTime()
    {
        return cookingTimeSeconds;
    }

    public String getCookName()
    {
        return cookName;
    }
}
