package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;
import java.io.IOException;
import java.util.List;

/**
 * Created by 309_newpower on 30.12.2015.
 */
public class Order
{
    Tablet tablet;
    protected List<Dish> dishes;

    public Tablet getTablet()
    {
        return tablet;
    }

    public Order(Tablet tablet) throws IOException
    {
        this.tablet = tablet;
        initDishes();
    }

    protected void initDishes() throws IOException
    {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public int getTotalCookingTime(){
        int timeForCooking = 0;
        for (Dish dish : dishes){
            timeForCooking += dish.getDuration();
        }
        return timeForCooking;
    }

    public List<Dish> getDishes()
    {
        return dishes;
    }

    public boolean isEmpty(){
        return dishes.isEmpty();
    }

    @Override
    public String toString()
    {
        if (dishes.size() == 0)return "";
        else {
            return "Your order: " + dishes + " of " +  tablet;
        }
    }
}
