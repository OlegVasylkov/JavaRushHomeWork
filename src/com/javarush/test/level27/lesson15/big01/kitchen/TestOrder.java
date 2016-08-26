package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 309_newpower on 14.01.2016.
 */
public class TestOrder extends Order
{
    public TestOrder(Tablet tablet) throws IOException
    {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException
    {
        dishes = new ArrayList<>();
        for (int i = (int)(Math.random() * Dish.values().length); i < Dish.values().length+1; i++)
        {
            dishes.add(Dish.values()[(int)(Math.random()*Dish.values().length)]);
        }
    }
}
