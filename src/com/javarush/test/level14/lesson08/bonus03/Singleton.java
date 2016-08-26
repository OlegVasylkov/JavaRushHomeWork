package com.javarush.test.level14.lesson08.bonus03;

import java.text.SimpleDateFormat;

/**
 * Created by 309_newpower on 01.09.2015.
 */
public class Singleton
{
    private static Singleton singleton = new Singleton();
    private Singleton(){}
    public static Singleton getInstance(){
        return singleton;
    }
}
