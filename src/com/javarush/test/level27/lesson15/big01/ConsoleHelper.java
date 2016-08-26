package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 309_newpower on 30.12.2015.
 */
public class ConsoleHelper
{
    public final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException
    {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException
    {
        List<Dish> list = new LinkedList<>();
        writeMessage("Make your choice: " + Dish.allDishesToString());
        while (true){
            String s = reader.readLine();
            if (s.equalsIgnoreCase("exit"))break;
            try
            {
                list.add(Dish.valueOf(s));
            } catch (IllegalArgumentException e){
                writeMessage(String.format("%s is not detected", s));
            }
        }
        return list;
    }
}
