package com.javarush.test.level14.lesson08.home09;

/**
 * Created by 309_newpower on 31.08.2015.
 */
public class Hrivna extends Money
{
    public Hrivna(double amount)
    {
        super(amount);
    }

    @Override
    public String getCurrencyName()
    {
        return "HRN";
    }

}
