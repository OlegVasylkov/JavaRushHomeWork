package com.javarush.test.level33.lesson15.big01;

/**
 * Created by 309_newpower on 15.06.2016.
 */
public class ExceptionHandler extends Exception
{
    public static void log(Exception e){
        Helper.printMessage(e.getMessage());
    }
}
