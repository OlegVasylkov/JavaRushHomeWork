package com.javarush.test.level09.lesson02.task01;

/* Каждый метод должен возвращать свой StackTrace
Написать пять методов, которые вызывают друг друга. Каждый метод должен возвращать свой StackTrace.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        method1();
    }

    public static StackTraceElement[] method1()
    {
        method2();
        StackTraceElement[] elements1 = Thread.currentThread().getStackTrace();
        return elements1;
        //Напишите тут ваш код

    }

    public static StackTraceElement[] method2()
    {
        method3();
        StackTraceElement[] elements2 = Thread.currentThread().getStackTrace();
        return elements2;
        //Напишите тут ваш код

    }

    public static StackTraceElement[] method3()
    {
        method4();
        StackTraceElement[] elements3 = Thread.currentThread().getStackTrace();
        return elements3;
        //Напишите тут ваш код

    }

    public static StackTraceElement[] method4()
    {
        method5();
        StackTraceElement[] elements4 = Thread.currentThread().getStackTrace();
        return elements4;
        //Напишите тут ваш код

    }

    public static StackTraceElement[] method5()
    {
        StackTraceElement[] elements5 = Thread.currentThread().getStackTrace();
        return elements5;
        //Напишите тут ваш код

    }
}
