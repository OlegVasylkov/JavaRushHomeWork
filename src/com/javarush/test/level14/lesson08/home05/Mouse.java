package com.javarush.test.level14.lesson08.home05;

/**
 * Created by 309_newpower on 31.08.2015.
 */
public class Mouse implements CompItem
{
    @Override
    public String getName()
    {
        return this.getClass().getSimpleName();
    }
}
